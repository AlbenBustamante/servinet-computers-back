package com.servinetcomputers.api.core.audit.listener;

import com.servinetcomputers.api.core.util.enums.Role;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserEntity;
import jakarta.persistence.PrePersist;

public class AuditRole {
    @PrePersist
    public void onPrePersist(UserEntity user) {
        if (user.getRole() == null) {
            user.setRole(Role.CASHIER);
        }
    }
}
