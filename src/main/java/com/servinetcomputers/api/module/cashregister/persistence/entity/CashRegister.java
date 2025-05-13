package com.servinetcomputers.api.module.cashregister.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.AuditCashRegisterStatus;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.converter.CashRegisterStatusConverter;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.core.util.constants.CashRegisterConstants.DESCRIPTION_LENGTH;

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

    @Column(nullable = false, columnDefinition = "SMALLINT")
    private Integer numeral;

    @Column(nullable = false, length = DESCRIPTION_LENGTH)
    private String description;

    @Convert(converter = CashRegisterStatusConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private CashRegisterStatus status;
}
