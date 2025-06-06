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

import java.time.LocalDate;

/**
 * The platform transfer's model entity.
 */
@Entity
@Table(name = "platform_transfers")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Setter
@Getter
public class PlatformTransfer extends AuditableEntity {
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
