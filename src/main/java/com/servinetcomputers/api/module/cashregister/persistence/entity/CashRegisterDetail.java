package com.servinetcomputers.api.module.cashregister.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.core.audit.listener.AuditCashRegisterDetail;
import com.servinetcomputers.api.core.converter.CashRegisterDetailStatusConverter;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserEntity;
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

import java.time.LocalTime;

import static com.servinetcomputers.api.core.util.constants.CashRegisterDetailConstants.BASE_LENGTH;
import static com.servinetcomputers.api.core.util.constants.CashRegisterDetailConstants.BASE_OBSERVATION_LENGTH;

@Entity
@Table(name = "cash_register_details")
@EntityListeners(value = {AuditCashRegisterDetail.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class CashRegisterDetail extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_register_detail_id")
    private Integer id;

    @Column(nullable = false)
    private LocalTime[] workingHours;

    @Column(nullable = false, length = BASE_LENGTH)
    private String initialBase;

    @Column(length = BASE_LENGTH)
    private String finalBase;

    @Column(length = BASE_OBSERVATION_LENGTH)
    private String baseObservation;

    @Convert(converter = CashRegisterDetailStatusConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private CashRegisterDetailStatus status;

    @ManyToOne
    @JoinColumn(name = "cash_register_id", nullable = false)
    private CashRegister cashRegister;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public String getFullName() {
        return user.getName() + " " + user.getLastName();
    }
}
