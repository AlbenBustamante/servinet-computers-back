package com.servinetcomputers.api.domain.cashtransfer;

import com.servinetcomputers.api.audit.AuditAuditable;
import com.servinetcomputers.api.audit.Auditable;
import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterDetail;
import com.servinetcomputers.api.domain.safes.entity.Safe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "cash_transfers")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class CashTransfer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_transfer_id")
    private Integer id;

    @Column(nullable = false)
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "from_cash_register_detail_id")
    private CashRegisterDetail fromCashRegisterDetail;

    @ManyToOne
    @JoinColumn(name = "to_cash_register_detail_id")
    private CashRegisterDetail toCashRegisterDetail;

    @ManyToOne
    @JoinColumn(name = "from_safe_id")
    private Safe fromSafe;

    @ManyToOne
    @JoinColumn(name = "to_safe_id")
    private Safe toSafe;

}
