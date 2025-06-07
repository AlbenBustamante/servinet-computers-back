package com.servinetcomputers.api.module.transaction.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.core.audit.listener.AuditTransactionDetail;
import com.servinetcomputers.api.core.converter.TransactionDetailTypeConverter;
import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_details")
@EntityListeners(value = {AuditTransactionDetail.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class TransactionDetail extends AuditableEntity {
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
