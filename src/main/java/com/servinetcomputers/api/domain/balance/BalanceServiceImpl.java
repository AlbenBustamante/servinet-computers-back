package com.servinetcomputers.api.domain.balance;

import com.servinetcomputers.api.domain.DataResponse;
import com.servinetcomputers.api.domain.PageResponse;
import com.servinetcomputers.api.domain.balance.abs.BalanceMapper;
import com.servinetcomputers.api.domain.balance.abs.BalanceRepository;
import com.servinetcomputers.api.domain.balance.abs.IBalanceService;
import com.servinetcomputers.api.domain.balance.model.dto.BalanceRequest;
import com.servinetcomputers.api.domain.balance.model.dto.BalanceResponse;
import com.servinetcomputers.api.domain.platform.abs.PlatformRepository;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: " + request.name()));

        if (!platform.getIsAvailable()) {
            throw new NotFoundException("Plataforma no encontrada: " + request.name());
        }

        final var response = balanceMapper.toResponse(balanceRepository.save(balanceMapper.toEntity(request)));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }


    @Transactional(rollbackFor = AppException.class)
    // @Secured(value = CAMPUS_AUTHORITY)
    @Override
    public PageResponse<BalanceResponse> update(int balanceId, BalanceRequest request) {
        final var balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new NotFoundException("Saldo no encontrado: " + balanceId));

        if (!balance.getIsAvailable()) {
            throw new NotFoundException("Saldo no encontrado: " + balanceId);
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

        if (balance.isEmpty() || !balance.get().getIsAvailable()) {
            throw new NotFoundException("Saldo no encontrado: " + balanceId);
        }

        balance.get().setIsAvailable(false);

        balanceRepository.save(balance.get());

        return true;
    }

}
