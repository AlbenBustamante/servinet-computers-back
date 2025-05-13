package com.servinetcomputers.api.module.bankdeposit.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositDto;

/**
 * Creates a new bank deposit and an administrative expense (if it's provided).
 * <p>Receives a {@link CreateBankDepositDto} data model.</p>
 * <p>Returns a {@link BankDepositDto} as response.</p>
 */
public interface CreateBankDepositUseCase extends UseCase<BankDepositDto, CreateBankDepositDto> {
}
