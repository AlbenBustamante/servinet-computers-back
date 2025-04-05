package com.servinetcomputers.api.module.changelog.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.converter.CashRegisterDetailStatusConverter;
import com.servinetcomputers.api.core.converter.ChangeLogActionConverter;
import com.servinetcomputers.api.core.converter.ChangeLogTypeConverter;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "change_logs")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Setter
@Getter
public class ChangeLog extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "change_log_id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String previousData;

    @Column(columnDefinition = "TEXT")
    private String newData;

    @Convert(converter = ChangeLogActionConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private ChangeLogAction action;

    @Convert(converter = ChangeLogTypeConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private ChangeLogType type;

    @Convert(converter = CashRegisterDetailStatusConverter.class)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private CashRegisterDetailStatus currentStatus;

    @ManyToOne
    @JoinColumn(name = "cash_register_detail_id", nullable = false)
    private CashRegisterDetail cashRegisterDetail;
}