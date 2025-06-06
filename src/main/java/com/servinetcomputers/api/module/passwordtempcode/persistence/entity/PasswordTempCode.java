package com.servinetcomputers.api.module.passwordtempcode.persistence.entity;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.core.audit.listener.AuditPasswordTempCode;
import com.servinetcomputers.api.core.util.constants.UserConstants;
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

import java.time.LocalDateTime;

import static com.servinetcomputers.api.core.util.constants.PasswordTempCodeConstants.CODE_LENGTH;

@Entity
@Table(name = "password_temp_codes")
@EntityListeners(value = {AuditPasswordTempCode.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class PasswordTempCode extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_temp_code_id")
    private Integer id;

    @Column(nullable = false, length = CODE_LENGTH, unique = true)
    private String code;

    @Column(nullable = false, length = UserConstants.CODE_LENGTH)
    private String userCode;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "used_by_id")
    private UserEntity usedBy;
}
