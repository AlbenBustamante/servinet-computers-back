package com.servinetcomputers.api.core.audit.listener;

import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterEntity;
import jakarta.persistence.PrePersist;

public class AuditCashRegisterStatus {
    @PrePersist
    public void onPrePersist(CashRegisterEntity cashRegister) {
        if (cashRegister.getStatus() == null) {
            cashRegister.setStatus(CashRegisterStatus.AVAILABLE);
        }
    }
}
