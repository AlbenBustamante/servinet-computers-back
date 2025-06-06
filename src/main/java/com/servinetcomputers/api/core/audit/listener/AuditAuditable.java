package com.servinetcomputers.api.core.audit.listener;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.datetime.DateTimeService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuditAuditable {
    private final DateTimeService dateTimeService;

    @PrePersist
    public void onPrePersist(AuditableEntity auditable) {
        auditable.setEnabled(true);
        auditable.setCreatedDate(dateTimeService.now());
        auditable.setModifiedDate(dateTimeService.now());
    }

    @PreUpdate
    public void onPreUpdate(AuditableEntity auditable) {
        auditable.setModifiedDate(dateTimeService.now());
    }
}
