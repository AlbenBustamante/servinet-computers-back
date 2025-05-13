package com.servinetcomputers.api.module.bankdeposit.persistence;

import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDepositPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface JpaBankDepositPaymentRepository extends JpaRepository<BankDepositPayment, Integer> {
    Integer countByPlatformIdAndEnabledTrueAndCreatedDateBetween(Integer platformId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(bdp.value) FROM BankDepositPayment bdp " +
            "WHERE bdp.enabled = true " +
            "AND bdp.platform.id = :platformId " +
            "AND bdp.createdDate BETWEEN :startDate AND :endDate")
    Integer sumAllByPlatformIdAndEnabledTrueAndCreatedDateBetween(Integer platformId, LocalDateTime startDate, LocalDateTime endDate);
}
