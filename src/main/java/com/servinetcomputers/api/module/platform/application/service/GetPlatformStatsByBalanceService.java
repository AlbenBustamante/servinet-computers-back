package com.servinetcomputers.api.module.platform.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositPaymentPersistenceAdapter;
import com.servinetcomputers.api.module.platform.application.usecase.GetPlatformStatsByBalanceUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformStatsDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class GetPlatformStatsByBalanceService implements GetPlatformStatsByBalanceUseCase {
    private final PlatformTransferRepository platformTransferRepository;
    private final BankDepositPaymentPersistenceAdapter bankDepositPaymentPersistenceAdapter;

    @Override
    public PlatformStatsDto call(PlatformBalanceDto dto, LocalDate date) {
        final var startDate = date.atStartOfDay();
        final var endDate = date.plusDays(1).atStartOfDay();

        final var platform = dto.getPlatform();
        final var platformId = platform.getId();
        final var platformName = platform.getName();
        final var balanceId = dto.getId();
        final var initialBalance = dto.getInitialBalance();
        final var finalBalance = dto.getFinalBalance();
        final var transfersAmount = platformTransferRepository.getPlatformTransfersAmountBetween(platformId, date, date);
        final var transfersTotal = platformTransferRepository.getPlatformTransfersTotalBetween(platformId, date, date);
        final var bankDepositsAmount = bankDepositPaymentPersistenceAdapter.getAmountByPlatformIdBetween(platformId, startDate, endDate);
        final var bankDepositsTotal = bankDepositPaymentPersistenceAdapter.getTotalByPlatformIdBetween(platformId, startDate, endDate);

        final var total = initialBalance + transfersTotal + bankDepositsTotal - finalBalance;

        return PlatformStatsDto.builder()
                .platformId(platformId)
                .platformName(platformName)
                .balanceId(balanceId)
                .initialBalance(initialBalance)
                .finalBalance(finalBalance)
                .transfersAmount(transfersAmount)
                .transfersTotal(transfersTotal)
                .bankDepositsAmount(bankDepositsAmount)
                .bankDepositsTotal(bankDepositsTotal)
                .total(total)
                .build();
    }
}
