package com.servinetcomputers.api.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
public class AuditAuditable {
    private final ZoneId zoneId;

    @PrePersist
    public void onPrePersist(Auditable auditable) {
        auditable.setEnabled(true);
        auditable.setCreatedDate(LocalDateTime.now(zoneId));
        auditable.setModifiedDate(LocalDateTime.now(zoneId));
    }

    @PreUpdate
    public void onPreUpdate(Auditable auditable) {
        auditable.setModifiedDate(LocalDateTime.now(zoneId));
    }
}
