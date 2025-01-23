package com.servinetcomputers.api.domain.platform.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.platform.application.usecase.LoadPortalPlatformsUseCase;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.PortalPlatformDto;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformRepository;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformTransferRepository;
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
            final var transfersAmount = transferRepository.getPlatformTransfersAmount(platform.getId(), startDate, endDate);
            final var transfersTotal = transferRepository.getPlatformTransfersTotal(platform.getId(), startDate, endDate);

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

    private PlatformBalanceResponse getPlatformBalance(int platformId, LocalDateTime startDate, LocalDateTime endDate) {
        final var balance = balanceRepository.get(platformId, startDate, endDate);

        if (balance.isPresent()) {
            return balance.get();
        }

        final var request = new PlatformBalanceRequest(platformId, 0, 0);
        return balanceRepository.save(request);
    }
}
