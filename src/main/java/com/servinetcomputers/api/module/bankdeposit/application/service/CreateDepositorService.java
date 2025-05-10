package com.servinetcomputers.api.module.bankdeposit.application.service;

import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateDepositorUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositPersistenceAdapter;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateDepositorDto;
import com.servinetcomputers.api.module.bankdeposit.domain.repository.BankDepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class CreateDepositorService implements CreateDepositorUseCase {
    private final BankDepositRepository bankDepositRepository;
    private final BankDepositPersistenceAdapter adapter;

    @Override
    public BankDepositDto call(CreateDepositorDto dto) {
        final var bankDepositDto = adapter.enrollDepositor(dto);
        var totalCollected = 0;

        for (final var depositor : bankDepositDto.getDepositors()) {
            totalCollected += depositor.getValue();
        }

        bankDepositDto.setTotalCollected(totalCollected);

        System.out.println(bankDepositDto);

        return bankDepositDto;
    }
}
