package com.servinetcomputers.api.module.cashtransfer.persistence;

import com.servinetcomputers.api.module.cashtransfer.persistence.entity.CashTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaCashTransferRepository extends JpaRepository<CashTransfer, Integer> {
    List<CashTransfer> findAllByFromCashRegisterIdOrToCashRegisterIdOrFromSafeIdOrToSafeIdAndEnabledTrueAndCreatedDateBetween(int fromCashRegisterId, int toCashRegisterId, int fromSafeId, int toSafeId, LocalDateTime startDate, LocalDateTime endDate);
}
