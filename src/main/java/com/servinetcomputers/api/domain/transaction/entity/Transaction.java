package com.servinetcomputers.api.domain.transaction.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.AuditTransaction;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.domain.transaction.util.TransactionType;
import com.servinetcomputers.api.domain.transaction.util.TransactionTypeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.domain.transaction.util.TransactionConstants.DESCRIPTION_LENGTH;

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

    @Column(nullable = false, length = DESCRIPTION_LENGTH)
    private String description;

    @Convert(converter = TransactionTypeConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private TransactionType type;

}
