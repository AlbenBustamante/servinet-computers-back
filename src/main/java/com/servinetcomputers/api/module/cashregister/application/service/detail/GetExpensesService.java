package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetExpensesUseCase;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetExpensesService implements GetExpensesUseCase {
    private final ExpenseRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<ExpenseResponse> call(Integer cashRegisterDetailId) {
        return repository.getAllByCashRegisterDetailId(cashRegisterDetailId);
    }
}
