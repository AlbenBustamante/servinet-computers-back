package com.servinetcomputers.api.module.expense.persistence.repository;

import com.servinetcomputers.api.module.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.expense.persistence.JpaExpenseRepository;
import com.servinetcomputers.api.module.expense.persistence.mapper.ExpenseMapper;
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
    public List<ExpenseResponse> getAllByCashRegisterDetailId(int cashRegisterDetailId) {
        final var expenses = repository.findAllByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId);
        return mapper.toResponses(expenses);
    }

    @Override
    public List<ExpenseResponse> getAllByDiscountAndCodeCodeBetween(boolean discount, String code, LocalDateTime startDate, LocalDateTime endDate) {
        final var expenses = repository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, discount);
        return mapper.toResponses(expenses);
    }

    @Override
    public Integer sumValuesByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount) {
        final var expenses = repository.sumAllByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, discount);
        return expenses != null ? expenses : 0;
    }

    @Override
    public int sumValuesBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var values = repository.sumAllValuesByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return values != null ? values : 0;
    }
}
