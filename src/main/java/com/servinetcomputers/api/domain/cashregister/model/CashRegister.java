package com.servinetcomputers.api.domain.cashregister.model;

import com.servinetcomputers.api.audit.Auditable;
import com.servinetcomputers.api.audit.AuditableEnabled;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatusConverter;
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

@Entity
@Table(name = "cash_registers")
@EntityListeners(value = {AuditableEnabled.class, AuditingEntityListener.class})
@Getter
@Setter
public class CashRegister extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_register_id")
    private Integer id;

    @Column(nullable = false, unique = true, columnDefinition = "SMALLINT")
    private Integer numeral;

    @Convert(converter = CashRegisterStatusConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private CashRegisterStatus status;
}
