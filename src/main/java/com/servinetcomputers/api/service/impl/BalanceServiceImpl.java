package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.BalanceRequest;
import com.servinetcomputers.api.dto.response.BalanceResponse;
import com.servinetcomputers.api.dto.response.DataResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.exception.*;
import com.servinetcomputers.api.mapper.BalanceMapper;
import com.servinetcomputers.api.model.Balance;
import com.servinetcomputers.api.repository.BalanceRepository;
import com.servinetcomputers.api.repository.PlatformRepository;
import com.servinetcomputers.api.service.IBalanceService;
import com.servinetcomputers.api.util.formatter.ICurrencyFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.util.constants.LocalConstants.DEFAULT_ZONE;
import static com.servinetcomputers.api.util.constants.SecurityConstants.CAMPUS_AUTHORITY;
import static com.servinetcomputers.api.util.constants.SecurityConstants.USER_AUTHORITY;

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
    private final ICurrencyFormatter currencyFormatter;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = CAMPUS_AUTHORITY)
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

        final var response = balanceMapper.toResponse(balanceRepository.save(balanceMapper.toEntity(request)), currencyFormatter);

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = CAMPUS_AUTHORITY)
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
            final var entity = balanceMapper.toEntity(request);

            entity.setPlatform(platform);
            entity.setPlatformId(platform.getId());

            entities.add(entity);
        });

        final var response = balanceMapper.toResponses(balanceRepository.saveAll(entities), currencyFormatter);

        return new PageResponse<>(201, true, new DataResponse<>(response.size(), 1, 1, response));
    }

    @Transactional(readOnly = true)
    @Secured(value = {CAMPUS_AUTHORITY, USER_AUTHORITY})
    @Override
    public PageResponse<BalanceResponse> getAllByCampusId(int campusId) {
        final var campus = campusRepository.findById(campusId)
                .orElseThrow(() -> new CampusNotFoundException(campusId));

        if (!campus.getIsAvailable()) {
            throw new CampusUnavailableException(campusId);
        }

        final var startDate = LocalDateTime.of(LocalDate.now(DEFAULT_ZONE), LocalTime.MIN);
        final var endDate = LocalDateTime.of(LocalDate.now(DEFAULT_ZONE), LocalTime.now());

        final var responses = balanceMapper.toResponses(balanceRepository.findAllByCampusIdAndIsAvailableAndCreatedAtBetween(campusId, true, startDate, endDate), currencyFormatter);

        return new PageResponse<>(200, true, new DataResponse<>(responses.size(), 1, 1, responses));
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = CAMPUS_AUTHORITY)
    @Override
    public PageResponse<BalanceResponse> update(int balanceId, BalanceRequest request) {
        final var balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new BalanceNotFoundException(balanceId));

        if (!balance.getIsAvailable()) {
            throw new BalanceUnavailableException(balanceId);
        }

        balance.setInitialBalance(request.initialBalance() != null ? request.initialBalance() : balance.getInitialBalance());
        balance.setFinalBalance(request.finalBalance() != null ? request.finalBalance() : balance.getFinalBalance());

        final var response = balanceMapper.toResponse(balanceRepository.save(balance), currencyFormatter);

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Secured(value = CAMPUS_AUTHORITY)
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
