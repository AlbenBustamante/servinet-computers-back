package com.servinetcomputers.api.module.expense.domain.repository;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.expense.domain.dto.CreateExpenseDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {
    ExpenseDto save(CreateExpenseDto dto);

    ExpenseDto save(ExpenseDto dto);

    Optional<ExpenseDto> get(int expenseId);

    Optional<ExpenseDto> getDeleted(int expenseId);

    PageResponse<ExpenseDto> getAllByCashRegisterDetailId(int cashRegisterDetailId, Pageable pageable);

    List<ExpenseDto> getAllByCashRegisterDetailId(int cashRegisterDetailId);

    List<ExpenseDto> getAllByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount);

    List<ExpenseDto> getAllByDiscountAndCodeCodeBetween(boolean discount, String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumValuesByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount);

    int sumValuesBetween(LocalDateTime startDate, LocalDateTime endDate);
}
