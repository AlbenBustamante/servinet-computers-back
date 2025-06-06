package com.servinetcomputers.api.module.transaction.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.AuditTransactionDetail;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.converter.TransactionDetailTypeConverter;
import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_details")
@EntityListeners(value = {AuditTransactionDetail.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class TransactionDetail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_detail_id")
    private Integer id;

    @Column(nullable = false)
    private Integer value;

    @Column(nullable = false)
    private Integer commission;

    @Column(nullable = false)
    private LocalDateTime date;

    @Convert(converter = TransactionDetailTypeConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private TransactionDetailType type;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "cash_register_detail_id", nullable = false)
    private CashRegisterDetail cashRegisterDetail;
}
