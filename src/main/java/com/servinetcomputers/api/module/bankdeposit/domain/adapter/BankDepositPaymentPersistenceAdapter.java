package com.servinetcomputers.api.module.bankdeposit.domain.adapter;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositPaymentDto;

import java.time.LocalDateTime;

public interface BankDepositPaymentPersistenceAdapter {
    BankDepositPaymentDto save(CreateBankDepositPaymentDto dto);

    Integer getAmountByPlatformIdBetween(Integer platformId, LocalDateTime startDate, LocalDateTime endDate);

    Integer getTotalByPlatformIdBetween(Integer platformId, LocalDateTime startDate, LocalDateTime endDate);
}
