package com.servinetcomputers.api.module.bankdeposit.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.bankdeposit.application.usecase.GetBankDepositsBetweenUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.repository.BankDepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetBankDepositsBetweenService implements GetBankDepositsBetweenUseCase {
    private final BankDepositRepository repository;
    private final DateTimeService dateTimeService;

    @Transactional(readOnly = true)
    @Override
    public List<BankDepositDto> call(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            final var today = dateTimeService.dateNow();
            startDate = dateTimeService.getMinByDate(today);
            endDate = dateTimeService.now();
        }

        final var bankDeposits = repository.getAllBetween(startDate, endDate);

        for (final var bankDeposit : bankDeposits) {
            final var depositors = bankDeposit.getDepositors();
            var totalCollected = 0;

            for (final var depositor : depositors) {
                totalCollected += depositor.getValue();
            }

            bankDeposit.setTotalCollected(totalCollected);
        }

        return bankDeposits;
    }
}
