package com.servinetcomputers.api.module.bankdeposit.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositPaymentDto;

/**
 * Creates a new {@code BankDepositPayment}.
 * <p>Checks if the payments total is more than the collected amount.</p>
 * <p>If the payments total equals to collected amount, the status will be updated to {@code CLOSED}.</p>
 * <p>Receives a {@link CreateBankDepositPaymentDto} data.</p>
 * <p>Returns an updated {@link BankDepositDto} data.</p>
 */
public interface CreateBankDepositPaymentUseCase extends UseCase<BankDepositDto, CreateBankDepositPaymentDto> {
}
