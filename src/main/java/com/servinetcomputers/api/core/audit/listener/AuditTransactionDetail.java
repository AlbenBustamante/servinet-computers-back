package com.servinetcomputers.api.core.audit.listener;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.transaction.persistence.entity.TransactionDetail;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuditTransactionDetail {
    private final DateTimeService dateTimeService;

    @PrePersist
    public void prePersist(TransactionDetail transactionDetail) {
        if (transactionDetail.getDate() == null) {
            transactionDetail.setDate(dateTimeService.now());
        }
    }
}
