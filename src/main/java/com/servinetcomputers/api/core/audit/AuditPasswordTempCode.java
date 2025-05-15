package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.passwordtempcode.persistence.entity.PasswordTempCode;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuditPasswordTempCode {
    private final DateTimeService dateTimeService;

    @PrePersist
    public void prePersist(PasswordTempCode passwordTempCode) {
        if (passwordTempCode.getExpirationDate() == null) {
            final var now = dateTimeService.now();
            final var expirationDate = now.plusMinutes(5);
            passwordTempCode.setExpirationDate(expirationDate);
        }
    }
}
