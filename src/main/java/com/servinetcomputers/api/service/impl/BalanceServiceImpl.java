package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.BalanceRequest;
import com.servinetcomputers.api.dto.response.BalanceResponse;
import com.servinetcomputers.api.dto.response.DataResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.exception.BalanceNotFoundException;
import com.servinetcomputers.api.exception.BalanceUnavailableException;
import com.servinetcomputers.api.exception.CampusNotFoundException;
import com.servinetcomputers.api.exception.CampusUnavailableException;
import com.servinetcomputers.api.exception.PlatformNameNotFoundException;
import com.servinetcomputers.api.exception.PlatformUnavailableException;
import com.servinetcomputers.api.mapper.BalanceMapper;
import com.servinetcomputers.api.model.Balance;
import com.servinetcomputers.api.repository.BalanceRepository;
import com.servinetcomputers.api.repository.CampusRepository;
import com.servinetcomputers.api.repository.PlatformRepository;
import com.servinetcomputers.api.service.IBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@link IBalanceService} implementation.
 */
@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements IBalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;
    private final PlatformRepository platformRepository;
    private final CampusRepository campusRepository;

    @Override
    public PageResponse<BalanceResponse> register(BalanceRequest request) {
        final var platform = platformRepository.findByName(request.name())
                .orElseThrow(() -> new PlatformNameNotFoundException(request.name()));

        if (!platform.getIsAvailable()) {
            throw new PlatformUnavailableException(platform.getId());
        }

        final var campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new CampusNotFoundException(request.campusId()));

        if (!campus.getIsAvailable()) {
            throw new CampusUnavailableException(campus.getId());
        }

        final var response = balanceMapper.toResponse(balanceRepository.save(balanceMapper.toEntity(request)));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Override
    public PageResponse<BalanceResponse> createAllInitialBalancesByCampusId(int campusId) {
        final var campus = campusRepository.findById(campusId)
                .orElseThrow(() -> new CampusNotFoundException(campusId));

        if (campus.getIsAvailable().equals(Boolean.FALSE)) {
            throw new CampusUnavailableException(campusId);
        }

        final var platforms = campus.getPlatforms();

        final List<Balance> entities = new ArrayList<>(platforms.size());

        platforms.forEach((platform) -> {
            final var request = new BalanceRequest(platform.getName(), campusId, BigDecimal.ZERO, BigDecimal.ZERO);

            entities.add(balanceMapper.toEntity(request));
        });

        final var response = balanceMapper.toResponses(balanceRepository.saveAll(entities));

        return new PageResponse<>(201, true, new DataResponse<>(response.size(), 1, 1, response));
    }

    @Override
    public PageResponse<BalanceResponse> getAllByCampusId(int campusId) {
        final var campus = campusRepository.findById(campusId)
                .orElseThrow(() -> new CampusNotFoundException(campusId));

        if (!campus.getIsAvailable()) {
            throw new CampusUnavailableException(campusId);
        }

        final var responses = balanceMapper.toResponses(balanceRepository.findAllByCampusIdAndIsAvailable(campusId, true));

        return new PageResponse<>(200, true, new DataResponse<>(responses.size(), 1, 1, responses));
    }

    @Override
    public PageResponse<BalanceResponse> update(int balanceId, BalanceRequest request) {
        final var balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new BalanceNotFoundException(balanceId));

        if (!balance.getIsAvailable()) {
            throw new BalanceUnavailableException(balanceId);
        }

        balance.setInitialBalance(request.initialBalance() != null ? request.initialBalance() : balance.getInitialBalance());
        balance.setFinalBalance(request.finalBalance() != null ? request.finalBalance() : balance.getFinalBalance());

        final var response = balanceMapper.toResponse(balanceRepository.save(balance));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Override
    public boolean delete(int balanceId) {
        final var balance = balanceRepository.findById(balanceId);

        if (balance.isEmpty()) {
            throw new BalanceNotFoundException(balanceId);
        }

        if (!balance.get().getIsAvailable()) {
            throw new BalanceUnavailableException(balanceId);
        }

        balance.get().setIsAvailable(false);

        balanceRepository.save(balance.get());

        return true;
    }

}
