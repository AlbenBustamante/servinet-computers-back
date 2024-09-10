package com.servinetcomputers.api.domain.cashregister.entity;

import com.servinetcomputers.api.audit.AuditAuditable;
import com.servinetcomputers.api.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailConstants.BASE_LENGTH;
import static com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailConstants.BASE_OBSERVATION_LENGTH;

@Entity
@Table(name = "cash_register_details")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class CashRegisterDetail extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_register_detail_id")
    private Integer id;

    @Column(name = "cash_register_id", nullable = false)
    private Integer cashRegisterId;

    @Column(nullable = false)
    private String workingHours;

    @Column(nullable = false, length = BASE_LENGTH)
    private String initialBase;

    @Column(length = BASE_LENGTH)
    private String finalBase;

    @Column(length = BASE_OBSERVATION_LENGTH)
    private String baseObservation;

    @ManyToOne
    @JoinColumn(name = "cash_register_id", columnDefinition = "cash_register_id", insertable = false, updatable = false)
    private CashRegister cashRegister;

}
