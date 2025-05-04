package com.servinetcomputers.api.module.expense.application.usecase;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.expense.domain.dto.CreateExpenseDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import org.springframework.data.domain.Pageable;

/**
 * --- ORIENTED TO FRONT END ---
 * <p>Creates a new expense and returns an updated list.</p>
 * <p>Receives the {@link CreateExpenseDto} data.</p>
 * <p>Receives the {@link Pageable} to filter the responses.</p>
 * <p>Returns a {@link PageResponse} with the results.</p>
 */
public interface CreateExpenseUseCase extends UseCaseBiParam<PageResponse<ExpenseDto>, CreateExpenseDto, Pageable> {
}
