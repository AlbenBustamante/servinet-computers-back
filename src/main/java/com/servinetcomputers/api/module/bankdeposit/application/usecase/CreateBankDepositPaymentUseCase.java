package com.servinetcomputers.api.module.bankdeposit.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositPaymentDto;

/**
 * Creates a new {@code BankDepositPayment}.
 * Receives a {@link CreateBankDepositPaymentDto} data.
 * Returns a {@link BankDepositPaymentDto} data.
 */
public interface CreateBankDepositPaymentUseCase extends UseCase<BankDepositPaymentDto, CreateBankDepositPaymentDto> {
}
