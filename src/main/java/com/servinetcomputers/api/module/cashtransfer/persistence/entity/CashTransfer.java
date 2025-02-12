package com.servinetcomputers.api.module.cashtransfer.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.converter.CashBoxTypeConverter;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "cash_transfers")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class CashTransfer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_transfer_id")
    private Integer id;

    @Column(nullable = false)
    private Integer value;

    @Column(nullable = false)
    private Integer senderId;

    @Column(nullable = false)
    private Integer receiverId;

    @Convert(converter = CashBoxTypeConverter.class)
    @Column(nullable = false)
    private CashBoxType senderType;

    @Convert(converter = CashBoxTypeConverter.class)
    @Column(nullable = false)
    private CashBoxType receiverType;
}
