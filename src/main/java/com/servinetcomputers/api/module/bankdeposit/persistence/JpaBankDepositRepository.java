package com.servinetcomputers.api.module.bankdeposit.persistence;

import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaBankDepositRepository extends JpaRepository<BankDeposit, Integer> {
    List<BankDeposit> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
