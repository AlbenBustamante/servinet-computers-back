package com.servinetcomputers.api.module.expense.persistence;

import com.servinetcomputers.api.module.expense.persistence.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaExpenseRepository extends JpaRepository<Expense, Integer> {
    Optional<Expense> findByIdAndEnabledTrue(int id);

    @Query("SELECT SUM(e.value) FROM Expense e WHERE " +
            "e.enabled = true AND " +
            "e.cashRegisterDetail.id = :cashRegisterDetailId AND " +
            "e.discount = :discount")
    Integer sumAllByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount);

    @Query("SELECT SUM(e.value) FROM Expense e " +
            "WHERE e.enabled = true " +
            "AND e.createdDate BETWEEN :startDate AND :endDate")
    Integer sumAllValuesByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Page<Expense> findAllByCashRegisterDetailIdAndEnabledTrue(int cashRegisterDetailId, Pageable pageable);

    List<Expense> findAllByCashRegisterDetailIdAndDiscountAndEnabledTrue(int cashRegisterDetailId, boolean discount);

    List<Expense> findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(String createdBy, LocalDateTime startDate, LocalDateTime endDate, boolean discount);

    List<Expense> findAllByCashRegisterDetailIdAndEnabledTrue(int cashRegisterDetailId);
}
