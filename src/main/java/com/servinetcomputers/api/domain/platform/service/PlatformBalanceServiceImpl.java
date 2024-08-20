package com.servinetcomputers.api.domain.platform.service;

import com.servinetcomputers.api.domain.platform.abs.IPlatformBalanceService;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.abs.PlatformRepository;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.entity.PlatformBalance;
import com.servinetcomputers.api.exception.AlreadyExistsException;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@link IPlatformBalanceService} implementation.
 */
@Service
@RequiredArgsConstructor
public class PlatformBalanceServiceImpl implements IPlatformBalanceService {

    private final PlatformBalanceRepository platformBalanceRepository;
    private final PlatformBalanceMapper platformBalanceMapper;
    private final PlatformRepository platformRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public List<PlatformBalanceResponse> createInitialBalances() {
        if (existsCurrentDateBalances()) {
            throw new AlreadyExistsException("Los saldos del día ya fueron creados");
        }

        final var platforms = platformRepository.findAllByEnabledTrue();

        if (platforms.isEmpty()) {
            return List.of();
        }

        final List<PlatformBalance> platformBalances = new ArrayList<>();

        platforms.forEach(platform -> {
            final var request = new PlatformBalanceRequest(platform.getId(), 0, 0);
            final var entity = platformBalanceMapper.toEntity(request);

            platformBalances.add(entity);
        });

        return platformBalanceMapper.toResponses(platformBalanceRepository.saveAll(platformBalances));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsCurrentDateBalances() {
        final var balances = platformBalanceRepository.findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime.now(), LocalDateTime.now());

        return !balances.isEmpty();
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformBalanceResponse update(int balanceId, PlatformBalanceRequest request) {
        final var balance = platformBalanceRepository.findById(balanceId)
                .orElseThrow(() -> new NotFoundException("Saldo no encontrado: #" + balanceId));

        if (balance.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Saldo no encontrado: #" + balanceId);
        }

        balance.setInitialBalance(request.initialBalance() != null ? request.initialBalance() : balance.getInitialBalance());
        balance.setFinalBalance(request.finalBalance() != null ? request.finalBalance() : balance.getFinalBalance());

        return platformBalanceMapper.toResponse(platformBalanceRepository.save(balance));
    }

    @Override
    public boolean delete(int balanceId) {
        final var balance = platformBalanceRepository.findById(balanceId);

        if (balance.isEmpty() || !balance.get().getEnabled()) {
            throw new NotFoundException("Saldo no encontrado: #" + balanceId);
        }

        balance.get().setEnabled(false);

        platformBalanceRepository.save(balance.get());

        return true;
    }

}
