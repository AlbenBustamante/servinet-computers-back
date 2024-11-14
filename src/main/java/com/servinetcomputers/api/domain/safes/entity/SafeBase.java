package com.servinetcomputers.api.domain.safes.entity;

import com.servinetcomputers.api.audit.AuditAuditable;
import com.servinetcomputers.api.audit.Auditable;
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

import static com.servinetcomputers.api.domain.safes.util.SafeConstants.BASE_LENGTH;

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
