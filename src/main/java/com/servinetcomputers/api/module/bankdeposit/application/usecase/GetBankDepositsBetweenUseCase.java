package com.servinetcomputers.api.module.bankdeposit.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Gets a list of bank deposits between two dates.
 * <p>Receives {@code startDate} and {@code endDate} to fetch. If they are null, will fetch by the current day.</p>
 * <p>Returns a list with the results.</p>
 */
public interface GetBankDepositsBetweenUseCase extends UseCaseBiParam<List<BankDepositDto>, LocalDateTime, LocalDateTime> {
}
