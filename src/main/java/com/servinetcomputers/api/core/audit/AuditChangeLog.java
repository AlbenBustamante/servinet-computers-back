package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.module.changelog.persistence.entity.ChangeLog;
import jakarta.persistence.PrePersist;

public class AuditChangeLog {
    @PrePersist
    public void prePersist(ChangeLog changeLog) {
        if (changeLog.getCurrentStatus() == null) {
            changeLog.setCurrentStatus(changeLog.getCashRegisterDetail().getStatus());
        }

        if (changeLog.getAction().equals(ChangeLogAction.DELETE)) {
            changeLog.setNewData(null);
        }
    }
}
