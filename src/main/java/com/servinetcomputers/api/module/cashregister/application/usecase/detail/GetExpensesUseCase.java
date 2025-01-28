package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;

import java.util.List;

public interface GetExpensesUseCase extends UseCase<List<ExpenseResponse>, Integer> {
}
