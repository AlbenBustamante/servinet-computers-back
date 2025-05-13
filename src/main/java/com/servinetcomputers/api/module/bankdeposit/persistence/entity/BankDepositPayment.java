package com.servinetcomputers.api.module.bankdeposit.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.module.platform.persistence.entity.Platform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "bank_deposit_payments")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class BankDepositPayment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_deposit_payment_id")
    private Integer id;

    @Column(nullable = false)
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "bank_deposit_id", nullable = false)
    private BankDeposit bankDeposit;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;
}
