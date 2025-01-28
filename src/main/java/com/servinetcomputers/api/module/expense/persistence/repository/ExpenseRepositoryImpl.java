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
    public List<ExpenseResponse> getByCashRegisterDetailId(int cashRegisterDetailId, LocalDateTime startDate, LocalDateTime endDate) {
        final var expenses = repository.findAllByCashRegisterDetailIdAndEnabledTrueAndCreatedDateBetween(cashRegisterDetailId, startDate, endDate);
        return mapper.toResponses(expenses);
    }

    @Override
    public List<ExpenseResponse> getAllByDiscountAndCodeCodeBetween(boolean discount, String code, LocalDateTime startDate, LocalDateTime endDate) {
        final var expenses = repository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, discount);
        return mapper.toResponses(expenses);
    }

    @Override
    public Integer sumExpenses(String code, LocalDateTime startDate, LocalDateTime endDate) {
        final var expenses = repository.sumAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, false);
        return expenses != null ? expenses : 0;
    }

    @Override
    public Integer sumDiscounts(String code, LocalDateTime startDate, LocalDateTime endDate) {
        final var discounts = repository.sumAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, true);
        return discounts != null ? discounts : 0;
    }
}
