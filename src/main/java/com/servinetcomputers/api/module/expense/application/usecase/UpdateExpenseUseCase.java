package com.servinetcomputers.api.module.expense.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import com.servinetcomputers.api.module.expense.domain.dto.UpdateExpenseDto;

/**
 * Updates an current expense.
 * <p>Receives the {@code Expense ID}.</p>
 * <p>Receives a {@link UpdateExpenseDto}.</p>
 * <p>Returns a {@link ExpenseDto}.</p>
 */
public interface UpdateExpenseUseCase extends UseCaseBiParam<ExpenseDto, Integer, UpdateExpenseDto> {
}
