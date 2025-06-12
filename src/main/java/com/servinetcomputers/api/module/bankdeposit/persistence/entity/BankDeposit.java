package com.servinetcomputers.api.module.bankdeposit.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.core.converter.BankDepositStatusConverter;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.entity.CashRegisterDetailEntity;
import com.servinetcomputers.api.module.expense.persistence.entity.ExpenseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

import static com.servinetcomputers.api.core.util.constants.BankDepositConstants.COLLECTOR_LENGTH;

@Entity
@Table(name = "bank_deposits")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class BankDeposit extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_deposit_id")
    private Integer id;

    @Column(nullable = false, length = COLLECTOR_LENGTH)
    private String collector;

    @Convert(converter = BankDepositStatusConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private BankDepositStatus status;

    @ManyToOne
    @JoinColumn(name = "cash_register_detail_id", nullable = false)
    private CashRegisterDetailEntity cashRegisterDetail;

    @OneToOne
    @JoinColumn(name = "expense_id")
    private ExpenseEntity expense;

    @OneToMany(mappedBy = "bankDeposit", cascade = CascadeType.ALL)
    private Set<BankDepositCashRegisterDetail> cashRegisterDetails = new HashSet<>();

    @OneToMany(mappedBy = "bankDeposit")
    private Set<BankDepositPayment> payments = new HashSet<>();

    public void addDepositor(BankDepositCashRegisterDetail cashRegisterDetail) {
        cashRegisterDetail.setBankDeposit(this);
        cashRegisterDetails.add(cashRegisterDetail);
    }
}
