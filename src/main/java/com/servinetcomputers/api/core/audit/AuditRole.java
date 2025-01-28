package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.core.util.enums.Role;
import com.servinetcomputers.api.domain.user.persistence.entity.User;
import jakarta.persistence.PrePersist;

public class AuditRole {
    @PrePersist
    public void onPrePersist(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.CASHIER);
        }
    }
}
