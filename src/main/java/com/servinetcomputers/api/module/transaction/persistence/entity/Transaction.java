package com.servinetcomputers.api.module.transaction.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.AuditTransaction;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.converter.TransactionTypeConverter;
import com.servinetcomputers.api.core.util.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.core.util.constants.TransactionConstants.DESCRIPTION_LENGTH;

@Entity
@Table(name = "transactions")
@EntityListeners(value = {AuditTransaction.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class Transaction extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;

    @Column(nullable = false, length = DESCRIPTION_LENGTH, unique = true)
    private String description;

    @Column(nullable = false)
    private Integer uses;

    @Convert(converter = TransactionTypeConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private TransactionType type;
}
