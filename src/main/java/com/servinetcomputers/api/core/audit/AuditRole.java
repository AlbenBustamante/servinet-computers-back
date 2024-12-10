package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.domain.user.User;
import com.servinetcomputers.api.core.security.util.Role;
import jakarta.persistence.PrePersist;

public class AuditRole {
    @PrePersist
    public void onPrePersist(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.CASHIER);
        }
    }
}
