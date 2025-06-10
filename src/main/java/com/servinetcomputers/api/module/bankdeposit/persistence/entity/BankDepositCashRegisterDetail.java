package com.servinetcomputers.api.module.bankdeposit.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetailEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "bank_deposits_cash_register_details")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class BankDepositCashRegisterDetail extends AuditableEntity {
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
    private CashRegisterDetailEntity cashRegisterDetail;
}
