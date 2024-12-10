package com.servinetcomputers.api.domain.platform.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Column(name = "platform_id", nullable = false)
    private Integer platformId;

    @Column(nullable = false)
    private Integer value;

    @Column(name = "voucher_urls", columnDefinition = "text[]")
    private String[] voucherUrls;

    @ManyToOne
    @JoinColumn(name = "platform_id", insertable = false, updatable = false)
    private Platform platform;

}
