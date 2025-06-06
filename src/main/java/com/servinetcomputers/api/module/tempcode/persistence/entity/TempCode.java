package com.servinetcomputers.api.module.tempcode.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserEntity;
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

@Entity
@Table(name = "temp_codes")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class TempCode extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temp_code_id")
    private Integer id;

    @Column(nullable = false)
    private Integer code;

    @ManyToOne
    @JoinColumn(name = "used_by_id")
    private UserEntity usedBy;
}
