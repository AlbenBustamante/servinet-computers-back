package com.servinetcomputers.api.domain.cashregister.application.usecase.detail;

import com.servinetcomputers.api.domain.UseCase;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;

import java.util.List;

public interface GetExpensesUseCase extends UseCase<List<ExpenseResponse>, Integer> {
}
