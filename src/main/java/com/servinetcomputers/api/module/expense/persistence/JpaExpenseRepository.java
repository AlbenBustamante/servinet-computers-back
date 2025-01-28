package com.servinetcomputers.api.module.expense.persistence;

import com.servinetcomputers.api.module.expense.persistence.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query("SELECT SUM(e.value) FROM Expense e WHERE " +
            "e.createdBy = :createdBy AND " +
            "e.enabled = true AND " +
            "e.createdDate BETWEEN :startDate AND :endDate AND " +
            "e.discount = :discount")
    Integer sumAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(String createdBy, LocalDateTime startDate, LocalDateTime endDate, boolean discount);

    List<Expense> findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(String createdBy, LocalDateTime startDate, LocalDateTime endDate, boolean discount);

    List<Expense> findAllByCashRegisterDetailIdAndEnabledTrueAndCreatedDateBetween(int cashRegisterDetailId, LocalDateTime startDate, LocalDateTime endDate);

}
