package com.servinetcomputers.api.module.platform.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.platform.application.usecase.GetAdminPlatformDetailsUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.GetPlatformStatsByBalanceUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.LoadPlatformBalanceByIdUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.AdminPlatformDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GetAdminPlatformDetailsService implements GetAdminPlatformDetailsUseCase {
    private final PlatformTransferRepository transferRepository;
    private final LoadPlatformBalanceByIdUseCase loadPlatformBalanceByIdUseCase;
    private final GetPlatformStatsByBalanceUseCase getPlatformStatsByBalanceUseCase;

    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public AdminPlatformDto call(Integer platformId, LocalDate date) {
        final var balance = loadPlatformBalanceByIdUseCase.call(platformId, date);
        final var transfers = transferRepository.getAllByPlatformIdBetweenOrderByDateDesc(platformId, date, date);
        final var platform = getPlatformStatsByBalanceUseCase.call(balance, date);

        return new AdminPlatformDto(platform, transfers);
    }
}
