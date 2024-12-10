package com.servinetcomputers.api.domain.expense;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.TrueFalseConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.domain.expense.util.ExpenseConstants.DESCRIPTION_LENGTH;

@Entity
@Table(name = "expenses")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class Expense extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Integer id;

    @Column(name = "cash_register_detail_id", nullable = false)
    private Integer cashRegisterDetailId;

    @Column(nullable = false, length = DESCRIPTION_LENGTH)
    private String description;

    @Column(nullable = false)
    private Integer value;

    @Convert(converter = TrueFalseConverter.class)
    @Column(nullable = false)
    private Boolean discount;

    @ManyToOne
    @JoinColumn(name = "cash_register_detail_id", insertable = false, updatable = false)
    private CashRegisterDetail cashRegisterDetail;

}
