package com.servinetcomputers.api.module.passwordtempcode.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.AuditPasswordTempCode;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.util.constants.UserConstants;
import com.servinetcomputers.api.module.user.persistence.entity.User;
import jakarta.persistence.*;
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
public class PasswordTempCode extends Auditable {
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
    private User usedBy;
}
