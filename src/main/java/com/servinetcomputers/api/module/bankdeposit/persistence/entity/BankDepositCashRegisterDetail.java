package com.servinetcomputers.api.module.bankdeposit.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "bank_deposits_cash_register_details")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class BankDepositCashRegisterDetail extends Auditable {
    @EmbeddedId
    private BankDepositCashRegisterDetailPK id;

    @Column(nullable = false)
    private Integer value;

    @MapsId("bankDepositId")
    @ManyToOne
    @JoinColumn(name = "bank_deposit_id")
    private BankDeposit bankDeposit;

    @MapsId("cashRegisterDetailId")
    @ManyToOne
    @JoinColumn(name = "cash_register_detail_id")
    private CashRegisterDetail cashRegisterDetail;
}
