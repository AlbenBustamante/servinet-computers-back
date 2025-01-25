package com.servinetcomputers.api.domain.expense.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;

public interface CreateExpenseUseCase extends UseCase<ExpenseResponse, ExpenseRequest> {
}
