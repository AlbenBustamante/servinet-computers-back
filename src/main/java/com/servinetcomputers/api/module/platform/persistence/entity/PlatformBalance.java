package com.servinetcomputers.api.module.platform.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
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

/**
 * The balance's model entity.
 */
@Entity
@Table(name = "platform_balances")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class PlatformBalance extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_balance_id")
    private Integer id;

    @Column(nullable = false)
    private Integer initialBalance;

    @Column(nullable = false)
    private Integer finalBalance;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;
}
