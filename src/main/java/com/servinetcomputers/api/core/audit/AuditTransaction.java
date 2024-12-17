package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.domain.transaction.entity.Transaction;
import com.servinetcomputers.api.domain.transaction.util.TransactionType;
import jakarta.persistence.PrePersist;

public class AuditTransaction {
    @PrePersist
    public void prePersist(Transaction transaction) {
        if (transaction.getUses() == null) {
            transaction.setUses(1);
        }

        if (transaction.getType() == null) {
            transaction.setType(TransactionType.NORMAL);
        }
    }
}
