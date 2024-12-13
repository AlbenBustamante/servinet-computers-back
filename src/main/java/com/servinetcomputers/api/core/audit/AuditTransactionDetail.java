package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.domain.transaction.entity.TransactionDetail;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

public class AuditTransactionDetail {
    @PrePersist
    public void prePersist(TransactionDetail transactionDetail) {
        if (transactionDetail.getDate() == null) {
            transactionDetail.setDate(LocalDateTime.now());
        }
    }
}
