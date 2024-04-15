package com.servinetcomputers.api.domain.cashregister.entity;

import com.servinetcomputers.api.audit.AuditAuditable;
import com.servinetcomputers.api.audit.Auditable;
import jakarta.persistence.Column;
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

import static com.servinetcomputers.api.domain.cashregister.util.CashRegisterBalanceConstants.BASE_LENGTH;

@Entity
@Table(name = "cash_register_bases")
@EntityListeners(value = {AuditingEntityListener.class, AuditAuditable.class})
@Getter
@Setter
public class CashRegisterBase extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_register_base_id")
    private Integer id;

    @Column(name = "cash_register_detail_id", nullable = false)
    private Integer cashRegisterDetailId;

    @Column(nullable = false, length = BASE_LENGTH)
    private String initialBase;

    @Column(length = BASE_LENGTH)
    private String finalBase;

    @ManyToOne
    @JoinColumn(name = "cash_register_detail_id", referencedColumnName = "cash_register_detail_id", insertable = false, updatable = false)
    private CashRegisterDetail cashRegisterDetail;

}
