package com.servinetcomputers.api.module.bankdeposit.domain.adapter;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositPaymentDto;

public interface BankDepositPaymentPersistenceAdapter {
    BankDepositPaymentDto save(CreateBankDepositPaymentDto dto);
}
