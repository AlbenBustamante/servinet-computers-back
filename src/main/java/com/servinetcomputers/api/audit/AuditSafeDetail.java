package com.servinetcomputers.api.audit;

import com.servinetcomputers.api.domain.safes.entity.SafeDetail;
import jakarta.persistence.PrePersist;

public class AuditSafeDetail {
    @PrePersist
    public void onPrePersist(SafeDetail safeDetail) {
        if (safeDetail.getInitialBase() == null) {
            safeDetail.setInitialBase("0;0;0;0;0;0;0;0;0;0;0");
        }

        if (safeDetail.getFinalBase() == null) {
            safeDetail.setFinalBase(safeDetail.getInitialBase());
        }

        if (safeDetail.getCalculatedBase() == null) {
            safeDetail.setCalculatedBase(0);
        }
    }
}
