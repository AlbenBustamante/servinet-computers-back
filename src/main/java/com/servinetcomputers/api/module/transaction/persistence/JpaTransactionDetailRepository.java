package com.servinetcomputers.api.module.transaction.persistence;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.transaction.persistence.entity.TransactionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaTransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
    Optional<TransactionDetail> findByIdAndEnabledTrue(int id);

    List<TransactionDetail> findAllByCashRegisterDetailIdAndEnabledTrue(int cashRegisterDetailId);

    Page<TransactionDetail> findAllByCashRegisterDetailIdAndEnabledTrue(int cashRegisterDetailId, Pageable pageable);

    List<TransactionDetail> findAllByCreatedByAndEnabledTrueAndCreatedDateBetween(String createdBy, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(td.value) FROM TransactionDetail td " +
            "WHERE td.enabled = true " +
            "AND td.cashRegisterDetail.id = :cashRegisterDetailId " +
            "AND td.type = :type")
    Integer sumAllByCashRegisterDetailIdAndEnabledTrueAndType(int cashRegisterDetailId, TransactionDetailType type);

    Integer countByCashRegisterDetailIdAndEnabledTrue(int cashRegisterDetailId);

    Integer countByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(td.commission) FROM TransactionDetail td " +
            "WHERE td.enabled = true " +
            "AND td.createdDate BETWEEN :startDate AND :endDate")
    Integer sumAllCommissionByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(td.commission) FROM TransactionDetail td " +
            "WHERE td.enabled = true " +
            "AND td.cashRegisterDetail.id = :cashRegisterDetailId")
    Integer sumAllCommissionByEnabledTrueAndCashRegisterDetailId(int cashRegisterDetailId);
}
