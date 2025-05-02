package com.servinetcomputers.api.module.bankdeposit.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.converter.BankDepositStatusConverter;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.expense.persistence.entity.Expense;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.core.util.constants.BankDepositConstants.COLLECTOR_LENGTH;

@Entity
@Table
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class BankDeposit extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_deposit_id")
    private Integer id;

    @Column(nullable = false, length = COLLECTOR_LENGTH)
    private String collector;

    @Convert(converter = BankDepositStatusConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private BankDepositStatus status;

    @OneToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
}
