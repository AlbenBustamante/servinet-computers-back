package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.domain.transaction.entity.Transaction;
import com.servinetcomputers.api.domain.transaction.util.TransactionType;
import jakarta.persistence.PrePersist;

import java.util.Collections;

public class AuditTransaction {
    @PrePersist
    public void prePersist(Transaction transaction) {
        if (transaction.getType() == null) {
            transaction.setType(TransactionType.NORMAL);
        }

        if (transaction.getTransactionDetails() == null) {
            transaction.setTransactionDetails(Collections.emptyList());
        }
    }
}
