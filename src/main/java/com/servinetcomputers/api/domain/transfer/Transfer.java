package com.servinetcomputers.api.domain.transfer;

import com.servinetcomputers.api.audit.AuditAuditable;
import com.servinetcomputers.api.audit.Auditable;
import com.servinetcomputers.api.domain.platform.entity.Platform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

/**
 * The transfer's model entity.
 */
@Entity
@Table(name = "transfers")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Setter
@Getter
public class Transfer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Integer id;

    @Column(name = "platform_id", nullable = false)
    private Integer platformId;

    @Column(name = "campus_id", nullable = false)
    private Integer campusId;

    @Column(nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "platform_id", insertable = false, updatable = false)
    private Platform platform;

}
