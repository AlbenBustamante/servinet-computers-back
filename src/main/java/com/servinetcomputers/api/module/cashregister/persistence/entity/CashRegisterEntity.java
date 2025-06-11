package com.servinetcomputers.api.module.cashregister.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.core.audit.listener.AuditCashRegisterStatus;
import com.servinetcomputers.api.core.converter.CashRegisterStatusConverter;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.core.util.constants.CashRegisterConstants.DESCRIPTION_LENGTH;

/**
 * Modelo para la tabla de cajas registradoras.
 */
@Entity
@Table(name = "cash_registers")
@EntityListeners(value = {AuditCashRegisterStatus.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class CashRegisterEntity extends AuditableEntity {
    /**
     * ID auto generado.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_register_id")
    private Integer id;

    /**
     * Número de caja.
     */
    @Column(nullable = false, columnDefinition = "SMALLINT")
    private Integer numeral;

    /**
     * Descripción.
     */
    @Column(nullable = false, length = DESCRIPTION_LENGTH)
    private String description;

    /**
     * Estado actual.
     */
    @Convert(converter = CashRegisterStatusConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private CashRegisterStatus status;
}
