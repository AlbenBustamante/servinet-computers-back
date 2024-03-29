package com.servinetcomputers.api.audit;

import jakarta.persistence.PrePersist;

public class AuditableEnabled {

    @PrePersist
    public void onPrePersist(Auditable entity) {
        entity.setEnabled(true);
    }

}
