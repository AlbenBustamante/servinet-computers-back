package com.servinetcomputers.api.module.bankdeposit.application.service;

import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateBankDepositPaymentUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositPaymentPersistenceAdapter;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositPaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class CreateBankDepositPaymentService implements CreateBankDepositPaymentUseCase {
    private final BankDepositPaymentPersistenceAdapter adapter;

    @Override
    public BankDepositPaymentDto call(CreateBankDepositPaymentDto dto) {
        return adapter.save(dto);
    }
}
