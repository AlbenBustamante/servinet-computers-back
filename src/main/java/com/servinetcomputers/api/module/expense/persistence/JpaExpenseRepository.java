package com.servinetcomputers.api.module.expense.persistence;

import com.servinetcomputers.api.module.expense.persistence.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query("SELECT SUM(e.value) FROM Expense e WHERE " +
            "e.enabled = true AND " +
            "e.cashRegisterDetail.id = :cashRegisterDetailId AND " +
            "e.discount = :discount")
    Integer sumAllByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount);

    List<Expense> findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(String createdBy, LocalDateTime startDate, LocalDateTime endDate, boolean discount);

    List<Expense> findAllByCashRegisterDetailIdAndEnabledTrue(int cashRegisterDetailId);
}
