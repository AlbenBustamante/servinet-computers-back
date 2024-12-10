package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.domain.cashregister.entity.CashRegister;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import jakarta.persistence.PrePersist;

public class AuditCashRegisterStatus {
    @PrePersist
    public void onPrePersist(CashRegister cashRegister) {
        if (cashRegister.getStatus() == null) {
            cashRegister.setStatus(CashRegisterStatus.AVAILABLE);
        }
    }
}
