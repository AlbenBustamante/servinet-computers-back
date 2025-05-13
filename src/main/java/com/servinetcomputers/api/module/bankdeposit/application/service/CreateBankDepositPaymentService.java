package com.servinetcomputers.api.module.bankdeposit.application.service;

import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateBankDepositPaymentUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositPaymentPersistenceAdapter;
import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositPersistenceAdapter;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositPaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class CreateBankDepositPaymentService implements CreateBankDepositPaymentUseCase {
    private final BankDepositPaymentPersistenceAdapter adapter;
    private final BankDepositPersistenceAdapter bankDepositPersistenceAdapter;

    @Override
    public BankDepositDto call(CreateBankDepositPaymentDto dto) {
        var bankDeposit = bankDepositPersistenceAdapter.get(dto.bankDepositId());
        var totalCollected = 0;

        for (final var depositor : bankDeposit.getDepositors()) {
            totalCollected += depositor.getValue();
        }

        bankDeposit.setTotalCollected(totalCollected);

        var totalPayments = dto.value();

        for (final var payment : bankDeposit.getPayments()) {
            totalPayments += payment.getValue();
        }

        if (totalPayments > totalCollected) {
            final var message = "Estás superando la cantidad recolectada: %d";
            throw new BadRequestException(String.format(message, totalCollected));
        }

        final var newPayment = adapter.save(dto);
        bankDeposit.getPayments().add(newPayment);

        if (totalPayments == totalCollected) {
            bankDeposit = bankDepositPersistenceAdapter.setStatus(dto.bankDepositId(), BankDepositStatus.CLOSED);
            bankDeposit.setTotalCollected(totalCollected);
        }

        return bankDeposit;
    }
}
