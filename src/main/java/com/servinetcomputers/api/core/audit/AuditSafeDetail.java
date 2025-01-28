package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.domain.safes.persistence.entity.SafeDetail;
import jakarta.persistence.PrePersist;

import static com.servinetcomputers.api.core.util.constants.SafeConstants.DEFAULT_BASE;

public class AuditSafeDetail {
    @PrePersist
    public void onPrePersist(SafeDetail safeDetail) {
        if (safeDetail.getInitialBase() == null || safeDetail.getInitialBase().isEmpty()) {
            safeDetail.setInitialBase(DEFAULT_BASE);
        }

        if (safeDetail.getFinalBase() == null || safeDetail.getFinalBase().isEmpty()) {
            safeDetail.setFinalBase(safeDetail.getInitialBase());
        }

        if (safeDetail.getCalculatedBase() == null) {
            safeDetail.setCalculatedBase(0);
        }
    }
}
