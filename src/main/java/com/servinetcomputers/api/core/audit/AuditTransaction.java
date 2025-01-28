package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.core.util.enums.TransactionType;
import com.servinetcomputers.api.domain.transaction.persistence.entity.Transaction;
import jakarta.persistence.PrePersist;

public class AuditTransaction {
    @PrePersist
    public void prePersist(Transaction transaction) {
        transaction.setDescription(transaction.getDescription().toUpperCase());

        if (transaction.getUses() == null) {
            transaction.setUses(1);
        }

        if (transaction.getType() == null) {
            transaction.setType(TransactionType.NORMAL);
        }
    }
}
