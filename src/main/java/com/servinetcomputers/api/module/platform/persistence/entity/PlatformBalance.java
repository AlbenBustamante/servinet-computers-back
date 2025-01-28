package com.servinetcomputers.api.module.platform.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * The balance's model entity.
 */
@Entity
@Table(name = "platform_balances")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class PlatformBalance extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_balance_id")
    private Integer id;

    @Column(nullable = false, name = "platform_id")
    private Integer platformId;

    @Column(nullable = false)
    private Integer initialBalance;

    @Column(nullable = false)
    private Integer finalBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", updatable = false, insertable = false)
    private Platform platform;

}
