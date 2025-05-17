package com.servinetcomputers.api.module.platform.application.service;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.platform.application.usecase.GetAdminPlatformDetailsUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.AdminPlatformDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GetAdminPlatformDetailsService implements GetAdminPlatformDetailsUseCase {
    private final PlatformRepository repository;
    private final PlatformBalanceRepository balanceRepository;
    private final PlatformTransferRepository transferRepository;

    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public AdminPlatformDto call(Integer platformId, YearMonth month) {
        final var platform = repository.get(platformId)
                .orElseThrow(() -> new NotFoundException("No se encontró la plataforma solicitada: " + platformId));

        final var startDate = month.atDay(1).atStartOfDay();
        final var endDate = month.plusMonths(1).atDay(1).atStartOfDay();

        final var balances = balanceRepository.getAllByPlatformIdBetweenOrderByCreatedDateDesc(platformId, startDate, endDate);
        final var transfers = transferRepository.getAllByPlatformIdBetweenOrderByCreatedDateDesc(platformId, startDate, endDate);

        return new AdminPlatformDto(platform, balances, transfers);
    }
}
