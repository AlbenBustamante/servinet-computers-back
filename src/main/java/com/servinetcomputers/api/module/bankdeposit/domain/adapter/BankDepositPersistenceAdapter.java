package com.servinetcomputers.api.module.bankdeposit.domain.adapter;

import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateDepositorDto;

public interface BankDepositPersistenceAdapter {
    BankDepositDto enrollDepositor(CreateDepositorDto createDepositorDto);

    BankDepositDto setStatus(Integer bankDepositId, BankDepositStatus status);

    BankDepositDto get(Integer bankDepositId);
}
