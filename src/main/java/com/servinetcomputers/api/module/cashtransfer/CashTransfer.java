package com.servinetcomputers.api.module.cashtransfer;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import com.servinetcomputers.api.module.safes.persistence.entity.SafeDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.core.util.constants.SafeConstants.BASE_LENGTH;

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

    @Column(length = BASE_LENGTH)
    private String safeBase;

    @ManyToOne
    @JoinColumn(name = "from_cash_register_detail_id")
    private CashRegisterDetail fromCashRegister;

    @ManyToOne
    @JoinColumn(name = "to_cash_register_detail_id")
    private CashRegisterDetail toCashRegister;

    @ManyToOne
    @JoinColumn(name = "from_safe_detail_id")
    private SafeDetail fromSafe;

    @ManyToOne
    @JoinColumn(name = "to_safe_detail_id")
    private SafeDetail toSafe;
}
