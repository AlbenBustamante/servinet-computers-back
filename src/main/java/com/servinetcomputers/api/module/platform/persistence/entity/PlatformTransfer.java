package com.servinetcomputers.api.module.platform.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

/**
 * The platform transfer's model entity.
 */
@Entity
@Table(name = "platform_transfers")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Setter
@Getter
public class PlatformTransfer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_transfer_id")
    private Integer id;

    @Column(nullable = false)
    private Integer value;

    @Column(name = "voucher_urls", columnDefinition = "text[]")
    private String[] voucherUrls;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;
}
