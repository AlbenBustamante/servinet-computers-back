package com.servinetcomputers.api.module.platform.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.platform.application.usecase.LoadPlatformBalanceByIdUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.LoadPortalPlatformsUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.PortalPlatformDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoadPortalPlatformsService implements LoadPortalPlatformsUseCase {
    private final PlatformRepository repository;
    private final PlatformTransferRepository transferRepository;
    private final DateTimeService dateTimeService;
    private final LoadPlatformBalanceByIdUseCase loadPlatformBalanceByIdUseCase;

    /**
     * Get all the available portal platforms.
     *
     * @return the platforms.
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<PortalPlatformDto> call() {
        final var platforms = repository.getAll();

        if (platforms.isEmpty()) {
            return List.of();
        }

        final var today = dateTimeService.dateNow();
        final List<PortalPlatformDto> platformReports = new ArrayList<>();

        platforms.forEach(platform -> {
            final var balance = loadPlatformBalanceByIdUseCase.call(platform.getId(), today);
            final var transfersAmount = transferRepository.getPlatformTransfersAmountBetween(platform.getId(), today, today);
            final var transfersTotal = transferRepository.getPlatformTransfersTotalBetween(platform.getId(), today, today);

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
}
