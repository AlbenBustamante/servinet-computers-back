package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterDetail;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailStatus;
import jakarta.persistence.PrePersist;

public class AuditCashRegisterDetail {
    @PrePersist
    public void prePersist(CashRegisterDetail cashRegisterDetail) {
        if (cashRegisterDetail.getStatus() == null) {
            cashRegisterDetail.setStatus(CashRegisterDetailStatus.WORKING);
        }
    }
}
