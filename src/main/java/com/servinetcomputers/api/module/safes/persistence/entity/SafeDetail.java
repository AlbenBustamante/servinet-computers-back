package com.servinetcomputers.api.module.safes.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.AuditSafeDetail;
import com.servinetcomputers.api.core.audit.Auditable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

import static com.servinetcomputers.api.core.util.constants.SafeConstants.BASE_LENGTH;

@Entity
@Table(name = "safe_details")
@EntityListeners(value = {AuditSafeDetail.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class SafeDetail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "safe_detail_id")
    private Integer id;

    @Column(nullable = false, length = BASE_LENGTH)
    private String initialBase;

    @Column(nullable = false, length = BASE_LENGTH)
    private String finalBase;

    @Column(nullable = false)
    private Integer calculatedBase;

    @ManyToOne
    @JoinColumn(name = "safe_id", nullable = false)
    private Safe safe;

    @OneToMany(mappedBy = "safeDetail", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<SafeBase> bases = new HashSet<>();
}
