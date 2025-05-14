package com.servinetcomputers.api.module.passwordtempcode.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.module.user.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "password_temp_codes")
@EntityListeners(value = {AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class PasswordTempCode extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_temp_code_id")
    private Integer id;

    @Column(nullable = false, length = 8, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "used_by", updatable = false)
    private User usedBy;
}
