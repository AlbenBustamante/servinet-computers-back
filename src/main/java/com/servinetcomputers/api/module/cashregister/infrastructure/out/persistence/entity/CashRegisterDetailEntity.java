package com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.core.audit.listener.AuditCashRegisterDetail;
import com.servinetcomputers.api.core.converter.CashRegisterDetailStatusConverter;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;

import static com.servinetcomputers.api.core.util.constants.CashRegisterDetailConstants.BASE_LENGTH;
import static com.servinetcomputers.api.core.util.constants.CashRegisterDetailConstants.BASE_OBSERVATION_LENGTH;

/**
 * Modelo para la tabla de movimiento de caja registradoras.
 */
@Entity
@Table(name = "cash_register_details")
@EntityListeners(value = {AuditCashRegisterDetail.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class CashRegisterDetailEntity extends AuditableEntity {
    /**
     * ID auto generado.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_register_detail_id")
    private Integer id;

    /**
     * Arreglo de horas.
     * <p>1. Hora Entrada. 2. Entrada Almuerzo. 3. Salida Almuerzo. 4. Salida Almuerzo.</p>
     */
    @Column(nullable = false)
    private LocalTime[] workingHours;

    /**
     * Base inicial con formato específico.
     */
    @Column(nullable = false, length = BASE_LENGTH)
    private String initialBase;

    /**
     * Base final con formato específico.
     */
    @Column(length = BASE_LENGTH)
    private String finalBase;

    /**
     * Observación de la base inicial.
     */
    @Column(length = BASE_OBSERVATION_LENGTH)
    private String baseObservation;

    /**
     * Estado actual.
     */
    @Convert(converter = CashRegisterDetailStatusConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private CashRegisterDetailStatus status;

    /**
     * Caja registradora.
     *
     * @see CashRegisterEntity
     */
    @ManyToOne
    @JoinColumn(name = "cash_register_id", nullable = false)
    private CashRegisterEntity cashRegister;

    /**
     * Usuario/empleado.
     *
     * @see UserEntity
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    /**
     * @return primer nombre y apellido.
     */
    public String getFullName() {
        return user.getName() + " " + user.getLastName();
    }
}
