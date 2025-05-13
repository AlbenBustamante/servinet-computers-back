package com.servinetcomputers.api.module.bankdeposit.application.service;

import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateDepositorUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositPersistenceAdapter;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateDepositorDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class CreateDepositorService implements CreateDepositorUseCase {
    private final CashRegisterDetailPersistenceAdapter cashRegisterDetailPersistenceAdapter;
    private final BankDepositPersistenceAdapter adapter;

    @Override
    public BankDepositDto call(CreateDepositorDto dto) {
        var bankDepositDto = adapter.enrollDepositor(dto);

        final var currentAmount = cashRegisterDetailPersistenceAdapter.getCurrentAmount();
        final var currentDepositors = bankDepositDto.getDepositors().size();

        if (currentDepositors == currentAmount) {
            bankDepositDto = adapter.setStatus(dto.getPk().bankDepositId(), BankDepositStatus.IN_PROGRESS);
        }

        var totalCollected = 0;

        for (final var depositor : bankDepositDto.getDepositors()) {
            totalCollected += depositor.getValue();
        }

        bankDepositDto.setTotalCollected(totalCollected);

        return bankDepositDto;
    }
}
