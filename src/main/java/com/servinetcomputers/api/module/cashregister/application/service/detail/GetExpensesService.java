package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetExpensesUseCase;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetExpensesService implements GetExpensesUseCase {
    private final ExpenseRepository repository;

    @Transactional(readOnly = true)
    @Override
    public PageResponse<ExpenseResponse> call(Integer cashRegisterDetailId, Pageable pageable) {
        return repository.getAllByCashRegisterDetailId(cashRegisterDetailId, pageable);
    }
}
