package com.servinetcomputers.api.audit;

import com.servinetcomputers.api.domain.safes.Safe;
import jakarta.persistence.PrePersist;

public class AuditSafeBase {
    @PrePersist
    public void onPrePersist(Safe safe) {
        if (safe.getInitialBase() == null) {
            safe.setInitialBase("0;0;0;0;0;0;0;0;0;0;0");
        }

        if (safe.getFinalBase() == null) {
            safe.setFinalBase(safe.getInitialBase());
        }
    }
}
