package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import com.servinetcomputers.api.domain.cashregister.persistence.entity.CashRegister;
import jakarta.persistence.PrePersist;

public class AuditCashRegisterStatus {
    @PrePersist
    public void onPrePersist(CashRegister cashRegister) {
        if (cashRegister.getStatus() == null) {
            cashRegister.setStatus(CashRegisterStatus.AVAILABLE);
        }
    }
}
