package com.servinetcomputers.api.domain.expense.domain.repository;

import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository {
    ExpenseResponse save(ExpenseRequest request);

    List<ExpenseResponse> getByCashRegisterDetailId(int cashRegisterDetailId, LocalDateTime startDate, LocalDateTime endDate);

    List<ExpenseResponse> getAllByDiscountAndCodeCodeBetween(boolean discount, String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumExpenses(String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumDiscounts(String code, LocalDateTime startDate, LocalDateTime endDate);
}
