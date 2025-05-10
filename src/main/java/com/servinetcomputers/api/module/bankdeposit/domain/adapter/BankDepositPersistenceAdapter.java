package com.servinetcomputers.api.module.bankdeposit.domain.adapter;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateDepositorDto;

public interface BankDepositPersistenceAdapter {
    BankDepositDto enrollDepositor(CreateDepositorDto createDepositorDto);
}
