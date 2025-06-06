package com.servinetcomputers.api.module.user.infrastructure.out.persistence;

import com.servinetcomputers.api.core.audit.infra.AuditableEntity;
import com.servinetcomputers.api.core.audit.listener.AuditAuditable;
import com.servinetcomputers.api.core.audit.listener.AuditRole;
import com.servinetcomputers.api.core.converter.RoleConverter;
import com.servinetcomputers.api.core.util.enums.Role;
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

import static com.servinetcomputers.api.core.util.constants.UserConstants.CODE_LENGTH;
import static com.servinetcomputers.api.core.util.constants.UserConstants.LAST_NAME_LENGTH;
import static com.servinetcomputers.api.core.util.constants.UserConstants.NAME_LENGTH;

/**
 * Modelo para la tabla de usuarios.
 */
@Entity
@Table(name = "users")
@EntityListeners(value = {AuditRole.class, AuditAuditable.class, AuditingEntityListener.class})
@Getter
@Setter
public class UserEntity extends AuditableEntity {
    /**
     * ID autogenerado.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    /**
     * Primer nombre.
     */
    @Column(nullable = false, length = NAME_LENGTH)
    private String name;

    /**
     * Apellido.
     */
    @Column(nullable = false, length = LAST_NAME_LENGTH)
    private String lastName;

    /**
     * Correo electrónico.
     */
    @Column(nullable = false)
    private String email;

    /**
     * Código único.
     */
    @Column(nullable = false, length = CODE_LENGTH, unique = true)
    private String code;

    /**
     * Contraseña encriptada.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Rol asignado.
     */
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    @Convert(converter = RoleConverter.class)
    private Role role;
}
