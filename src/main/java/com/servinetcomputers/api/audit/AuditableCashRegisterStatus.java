package com.servinetcomputers.api.audit;

import com.servinetcomputers.api.domain.cashregister.CashRegister;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import jakarta.persistence.PrePersist;

public class AuditableCashRegisterStatus {
    @PrePersist
    public void onPrePersist(CashRegister cashRegister) {
        if (cashRegister.getStatus() == null) {
            cashRegister.setStatus(CashRegisterStatus.AVAILABLE);
        }
    }
}
