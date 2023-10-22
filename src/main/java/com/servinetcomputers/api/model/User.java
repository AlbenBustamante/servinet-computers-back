package com.servinetcomputers.api.model;

import com.servinetcomputers.api.util.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.servinetcomputers.api.util.constants.UserConstants.EMAIL_LENGTH;
import static com.servinetcomputers.api.util.constants.UserConstants.LAST_NAME_LENGTH;
import static com.servinetcomputers.api.util.constants.UserConstants.NAME_LENGTH;
import static com.servinetcomputers.api.util.constants.UserConstants.ROLE_LENGTH;

/**
 * The user's model entity.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false, length = NAME_LENGTH)
    private String name;

    @Column(nullable = false, length = LAST_NAME_LENGTH)
    private String lastName;

    @Column(nullable = false, length = EMAIL_LENGTH, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = ROLE_LENGTH)
    private Role role;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (role == null) {
            role = Role.USER;
        }

        isAvailable = true;
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
