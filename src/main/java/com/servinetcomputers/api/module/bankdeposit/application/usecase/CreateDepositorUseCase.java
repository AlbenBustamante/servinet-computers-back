package com.servinetcomputers.api.module.bankdeposit.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateDepositorDto;

/**
 * Adds a depositor to a current and active {@code BankDeposit}.
 * <p>Checks if there are remaining depositors.</p>
 * <p>If there are not remaining depositors, the {@code BankDeposit} will be closed.</p>
 * <p>Receives the {@link CreateDepositorDto} data.</p>
 * <p>Returns an updated {@link BankDepositDto} data.</p>
 */
public interface CreateDepositorUseCase extends UseCase<BankDepositDto, CreateDepositorDto> {
}
