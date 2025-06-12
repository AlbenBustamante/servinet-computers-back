package com.servinetcomputers.api.core.audit.listener;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.entity.CashRegisterDetailEntity;
import jakarta.persistence.PrePersist;

public class AuditCashRegisterDetail {
    @PrePersist
    public void prePersist(CashRegisterDetailEntity cashRegisterDetail) {
        if (cashRegisterDetail.getStatus() == null) {
            cashRegisterDetail.setStatus(CashRegisterDetailStatus.WORKING);
        }
    }
}
