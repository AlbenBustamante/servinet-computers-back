package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import org.springframework.data.domain.Pageable;

/**
 * Get the current expenses by a cash register detail.
 * <p>Receives the {@code CashRegisterDetail ID} to fetch.</p>
 * <p>Receives the {@link Pageable} data.</p>
 * <p>Returns a {@link PageResponse} with the results.</p>
 */
public interface GetExpensesUseCase extends UseCaseBiParam<PageResponse<ExpenseDto>, Integer, Pageable> {
}
