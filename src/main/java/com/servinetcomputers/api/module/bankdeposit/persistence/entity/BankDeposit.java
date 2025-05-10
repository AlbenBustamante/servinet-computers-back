package com.servinetcomputers.api.module.bankdeposit.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.converter.BankDepositStatusConverter;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import com.servinetcomputers.api.module.expense.persistence.entity.Expense;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @ManyToOne
    @JoinColumn(name = "cash_register_detail_id", nullable = false)
    private CashRegisterDetail cashRegisterDetail;

    @OneToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @OneToMany(mappedBy = "bankDeposit", cascade = CascadeType.ALL)
    private Set<BankDepositCashRegisterDetail> cashRegisterDetails = new HashSet<>();

    @OneToMany(mappedBy = "bankDeposit")
    private Set<BankDepositPayment> payments = new HashSet<>();

    public void addDepositor(BankDepositCashRegisterDetail cashRegisterDetail) {
        cashRegisterDetail.setBankDeposit(this);
        cashRegisterDetails.add(cashRegisterDetail);
    }
}
