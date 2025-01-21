package com.servinetcomputers.api.domain.transaction.abs;

import com.servinetcomputers.api.domain.transaction.entity.TransactionDetail;
import com.servinetcomputers.api.domain.transaction.util.TransactionDetailType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaTransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
    List<TransactionDetail> findAllByCashRegisterDetailIdAndEnabledTrueAndCreatedDateBetween(int cashRegisterDetailId, LocalDateTime startDate, LocalDateTime endDate);

    List<TransactionDetail> findAllByCreatedByAndEnabledTrueAndCreatedDateBetween(String createdBy, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(td.value) FROM TransactionDetail td " +
            "WHERE td.enabled = true " +
            "AND td.createdBy = :createdBy " +
            "AND td.createdDate BETWEEN :startDate AND :endDate " +
            "AND td.type = :type")
    Integer sumAllByCreatedByAndEnabledTrueAndCreatedDateBetween(
            String createdBy,
            LocalDateTime startDate,
            LocalDateTime endDate,
            TransactionDetailType type);
}
