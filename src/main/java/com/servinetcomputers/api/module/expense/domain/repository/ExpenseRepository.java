package com.servinetcomputers.api.module.expense.domain.repository;

import com.servinetcomputers.api.module.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {
    ExpenseResponse save(ExpenseRequest request);

    void save(ExpenseResponse response);

    Optional<ExpenseResponse> get(int expenseId);

    List<ExpenseResponse> getAllByCashRegisterDetailId(int cashRegisterDetailId);

    List<ExpenseResponse> getAllByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount);

    List<ExpenseResponse> getAllByDiscountAndCodeCodeBetween(boolean discount, String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumValuesByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount);

    int sumValuesBetween(LocalDateTime startDate, LocalDateTime endDate);
}
