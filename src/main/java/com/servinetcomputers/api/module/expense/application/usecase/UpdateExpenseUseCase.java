package com.servinetcomputers.api.module.expense.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.expense.domain.dto.UpdateExpenseDto;

/**
 * Updates an current expense.
 * <p>Receives the {@code Expense ID}.</p>
 * <p>Receives a {@link UpdateExpenseDto}.</p>
 * <p>Returns a {@link ExpenseResponse}.</p>
 */
public interface UpdateExpenseUseCase extends UseCaseBiParam<ExpenseResponse, Integer, UpdateExpenseDto> {
}
