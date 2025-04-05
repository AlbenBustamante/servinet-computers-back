package com.servinetcomputers.api.module.expense.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParamWithoutReturn;

/**
 * Deletes a current expenses by its ID.
 * <p>Receives the {@code Expense ID}.</p>
 * <p>Receives the {@code tempCode}.</p>
 */
public interface DeleteExpenseUseCase extends UseCaseBiParamWithoutReturn<Integer, Integer> {
}
