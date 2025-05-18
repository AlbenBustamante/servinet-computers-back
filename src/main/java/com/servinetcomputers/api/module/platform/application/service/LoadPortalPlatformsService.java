package com.servinetcomputers.api.module.platform.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.platform.application.usecase.LoadPortalPlatformsUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.PortalPlatformDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoadPortalPlatformsService implements LoadPortalPlatformsUseCase {
    private final PlatformRepository repository;
    private final PlatformBalanceRepository balanceRepository;
    private final PlatformTransferRepository transferRepository;
    private final DateTimeService dateTimeService;

    /**
     * Get all the available portal platforms.
     *
     * @return the platforms.
     */
    @Transactional(rollbackFor = AppException.class)
    @Override
    public List<PortalPlatformDto> call() {
        final var platforms = repository.getAll();

        if (platforms.isEmpty()) {
            return List.of();
        }

        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final List<PortalPlatformDto> platformReports = new ArrayList<>();

        platforms.forEach(platform -> {
            final var balance = getPlatformBalance(platform.getId(), startDate, endDate);
            final var transfersAmount = transferRepository.getPlatformTransfersAmountBetween(platform.getId(), startDate, endDate);
            final var transfersTotal = transferRepository.getPlatformTransfersTotalBetween(platform.getId(), startDate, endDate);

            final var report = new PortalPlatformDto(
                    platform.getId(),
                    platform.getName(),
                    balance.getId(),
                    balance.getInitialBalance(),
                    balance.getFinalBalance(),
                    transfersAmount,
                    transfersTotal
            );

            platformReports.add(report);
        });

        return platformReports;
    }

    private PlatformBalanceDto getPlatformBalance(int platformId, LocalDateTime startDate, LocalDateTime endDate) {
        final var balances = balanceRepository.getAllByPlatformIdBetween(platformId, startDate, endDate);

        if (!balances.isEmpty()) {
            return balances.get(0);
        }

        final var lastBalance = balanceRepository.getLastByPlatformId(platformId);
        final var newBalance = lastBalance.isPresent() ? lastBalance.get().getFinalBalance() : 0;
        final var platform = repository.get(platformId).orElseThrow(() -> new NotFoundException("No se encontró la plataforma: " + platformId));

        final var request = new CreatePlatformBalanceDto(newBalance, 0, platform);
        return balanceRepository.save(request);
    }
}
