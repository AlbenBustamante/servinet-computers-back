package com.servinetcomputers.api.module.expense.domain.repository;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {
    ExpenseResponse save(ExpenseRequest request);

    ExpenseResponse save(ExpenseResponse response);

    Optional<ExpenseResponse> get(int expenseId);

    PageResponse<ExpenseResponse> getAllByCashRegisterDetailId(int cashRegisterDetailId, Pageable pageable);

    List<ExpenseResponse> getAllByCashRegisterDetailId(int cashRegisterDetailId);

    List<ExpenseResponse> getAllByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount);

    List<ExpenseResponse> getAllByDiscountAndCodeCodeBetween(boolean discount, String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumValuesByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount);

    int sumValuesBetween(LocalDateTime startDate, LocalDateTime endDate);
}
