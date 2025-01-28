package com.servinetcomputers.api.domain.safes.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.core.util.constants.SafeConstants.BASE_LENGTH;

@Entity
@Table(name = "safe_bases")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class SafeBase extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "safe_base_id")
    private Integer id;

    @Column(nullable = false, length = BASE_LENGTH)
    private String base;

    @ManyToOne
    @JoinColumn(name = "safe_detail_id", nullable = false)
    private SafeDetail safeDetail;
}
