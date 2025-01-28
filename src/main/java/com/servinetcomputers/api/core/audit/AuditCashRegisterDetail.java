package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import jakarta.persistence.PrePersist;

public class AuditCashRegisterDetail {
    @PrePersist
    public void prePersist(CashRegisterDetail cashRegisterDetail) {
        if (cashRegisterDetail.getStatus() == null) {
            cashRegisterDetail.setStatus(CashRegisterDetailStatus.WORKING);
        }
    }
}
