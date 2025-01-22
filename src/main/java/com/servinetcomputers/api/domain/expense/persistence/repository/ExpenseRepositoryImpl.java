package com.servinetcomputers.api.domain.expense.persistence.repository;

import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.domain.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.domain.expense.persistence.JpaExpenseRepository;
import com.servinetcomputers.api.domain.expense.persistence.mapper.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final JpaExpenseRepository repository;
    private final ExpenseMapper mapper;

    @Override
    public ExpenseResponse save(ExpenseRequest request) {
        final var entity = mapper.toEntity(request);
        final var newExpense = repository.save(entity);

        return mapper.toResponse(newExpense);
    }

    @Override
    public List<ExpenseResponse> getByCashRegisterDetailId(int cashRegisterDetailId, LocalDateTime startDate, LocalDateTime endDate) {
        final var expenses = repository.findAllByCashRegisterDetailIdAndEnabledTrueAndCreatedDateBetween(cashRegisterDetailId, startDate, endDate);
        return mapper.toResponses(expenses);
    }
}
