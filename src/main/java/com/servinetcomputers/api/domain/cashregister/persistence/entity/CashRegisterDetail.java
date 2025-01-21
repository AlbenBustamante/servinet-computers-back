package com.servinetcomputers.api.domain.cashregister.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.AuditCashRegisterDetail;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailStatus;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailStatusConverter;
import com.servinetcomputers.api.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;

import static com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailConstants.BASE_LENGTH;
import static com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailConstants.BASE_OBSERVATION_LENGTH;

@Entity
@Table(name = "cash_register_details")
@EntityListeners(value = {AuditCashRegisterDetail.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class CashRegisterDetail extends Auditable {

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
    private User user;

}
