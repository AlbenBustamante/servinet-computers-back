package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.BalanceRequest;
import com.servinetcomputers.api.dto.response.BalanceResponse;
import com.servinetcomputers.api.dto.response.DataResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.BalanceNotFoundException;
import com.servinetcomputers.api.exception.BalanceUnavailableException;
import com.servinetcomputers.api.exception.PlatformNameNotFoundException;
import com.servinetcomputers.api.exception.PlatformUnavailableException;
import com.servinetcomputers.api.mapper.BalanceMapper;
import com.servinetcomputers.api.repository.BalanceRepository;
import com.servinetcomputers.api.repository.PlatformRepository;
import com.servinetcomputers.api.service.IBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = AppException.class)
    // @Secured(value = CAMPUS_AUTHORITY)
    @Override
    public PageResponse<BalanceResponse> register(BalanceRequest request) {
        final var platform = platformRepository.findByName(request.name())
                .orElseThrow(() -> new PlatformNameNotFoundException(request.name()));

        if (!platform.getIsAvailable()) {
            throw new PlatformUnavailableException(platform.getId());
        }

        final var response = balanceMapper.toResponse(balanceRepository.save(balanceMapper.toEntity(request)));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }


    @Transactional(rollbackFor = AppException.class)
    // @Secured(value = CAMPUS_AUTHORITY)
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

    // @Secured(value = CAMPUS_AUTHORITY)
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
