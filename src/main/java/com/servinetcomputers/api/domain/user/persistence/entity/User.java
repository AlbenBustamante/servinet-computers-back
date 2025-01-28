package com.servinetcomputers.api.domain.user.persistence.entity;

import com.servinetcomputers.api.core.audit.AuditAuditable;
import com.servinetcomputers.api.core.audit.AuditRole;
import com.servinetcomputers.api.core.audit.Auditable;
import com.servinetcomputers.api.core.converter.RoleConverter;
import com.servinetcomputers.api.core.util.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.servinetcomputers.api.core.util.constants.UserConstants.*;

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
