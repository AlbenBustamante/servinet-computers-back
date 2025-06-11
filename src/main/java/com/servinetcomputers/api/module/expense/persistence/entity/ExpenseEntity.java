package com.servinetcomputers.api.module.expense.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetailEntity;
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
import org.hibernate.type.TrueFalseConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.core.util.constants.ExpenseConstants.DESCRIPTION_LENGTH;

/**
 * Modelo para la tabla de gastos.
 */
@Entity
@Table(name = "expenses")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class ExpenseEntity extends AuditableEntity {
    /**
     * ID auto generado.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Integer id;

    /**
     * Descripción.
     */
    @Column(nullable = false, length = DESCRIPTION_LENGTH)
    private String description;

    /**
     * Valor.
     */
    @Column(nullable = false)
    private Integer value;

    /**
     * Descontar de la nómina.
     */
    @Convert(converter = TrueFalseConverter.class)
    @Column(nullable = false)
    private Boolean discount;

    /**
     * Administrativo.
     */
    @Column(nullable = false)
    private Boolean administrative;

    /**
     * Movimiento de caja.
     */
    @ManyToOne
    @JoinColumn(name = "cash_register_detail_id", nullable = false)
    private CashRegisterDetailEntity cashRegisterDetail;
}
