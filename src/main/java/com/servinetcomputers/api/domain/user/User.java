package com.servinetcomputers.api.domain.user;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.AuditRole;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.domain.user.util.RoleConverter;
import com.servinetcomputers.api.core.security.util.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.domain.user.util.UserConstants.CODE_LENGTH;
import static com.servinetcomputers.api.domain.user.util.UserConstants.LAST_NAME_LENGTH;
import static com.servinetcomputers.api.domain.user.util.UserConstants.NAME_LENGTH;

/**
 * The user's model entity.
 */
@Entity
@Table(name = "users")
@EntityListeners(value = {AuditRole.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false, length = NAME_LENGTH)
    private String name;

    @Column(nullable = false, length = LAST_NAME_LENGTH)
    private String lastName;

    @Column(nullable = false, length = CODE_LENGTH, unique = true)
    private String code;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    @Convert(converter = RoleConverter.class)
    private Role role;

}
