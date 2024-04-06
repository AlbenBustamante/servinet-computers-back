package com.servinetcomputers.api.domain.cashregister.entity;

import com.servinetcomputers.api.audit.AuditAuditable;
import com.servinetcomputers.api.audit.AuditCashRegisterStatus;
import com.servinetcomputers.api.audit.Auditable;
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

import static com.servinetcomputers.api.domain.cashregister.util.CashRegisterConstants.DESCRIPTION_LENGTH;

@Entity
@Table(name = "cash_registers")
@EntityListeners(value = {AuditCashRegisterStatus.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class CashRegister extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_register_id")
    private Integer id;

    @Column(nullable = false, unique = true, columnDefinition = "SMALLINT")
    private Integer numeral;

    @Column(nullable = false, length = DESCRIPTION_LENGTH)
    private String description;

    @Convert(converter = CashRegisterStatusConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private CashRegisterStatus status;

}
