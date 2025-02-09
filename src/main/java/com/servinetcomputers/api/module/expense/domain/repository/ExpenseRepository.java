package com.servinetcomputers.api.module.expense.domain.repository;

import com.servinetcomputers.api.module.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository {
    ExpenseResponse save(ExpenseRequest request);

    List<ExpenseResponse> getAllByCashRegisterDetailIdBetween(int cashRegisterDetailId, LocalDateTime startDate, LocalDateTime endDate);

    List<ExpenseResponse> getAllByDiscountAndCodeCodeBetween(boolean discount, String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumExpenses(String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumDiscounts(String code, LocalDateTime startDate, LocalDateTime endDate);
}
