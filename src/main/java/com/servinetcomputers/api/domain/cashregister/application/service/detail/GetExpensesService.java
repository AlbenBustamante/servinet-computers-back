package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.GetExpensesUseCase;
import com.servinetcomputers.api.domain.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;
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
    public List<ExpenseResponse> call(Integer param) {
        return repository.getByCashRegisterDetailId(param);
    }
}
