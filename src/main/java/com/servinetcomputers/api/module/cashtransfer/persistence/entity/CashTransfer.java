package com.servinetcomputers.api.module.cashtransfer.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.converter.CashBoxTypeConverter;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
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

import static com.servinetcomputers.api.core.util.constants.SafeConstants.BASE_LENGTH;

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

    @Column(length = BASE_LENGTH)
    private String safeBase;

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
