package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
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
    private final DateTimeService dateTimeService;

    @Transactional(readOnly = true)
    @Override
    public List<ExpenseResponse> call(Integer param) {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        return repository.getByCashRegisterDetailId(param, startDate, endDate);
    }
}
