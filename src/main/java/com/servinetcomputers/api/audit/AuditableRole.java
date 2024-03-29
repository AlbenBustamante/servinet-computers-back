package com.servinetcomputers.api.audit;

import com.servinetcomputers.api.domain.user.model.User;
import com.servinetcomputers.api.security.util.Role;
import jakarta.persistence.PrePersist;

public class AuditableRole {

    @PrePersist
    public void onPrePersist(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.CASHIER);
        }
    }

}
