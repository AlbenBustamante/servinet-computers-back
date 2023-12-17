package com.servinetcomputers.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.servinetcomputers.api.util.constants.LocaleConstants.LOCALE_ZONE;
import static com.servinetcomputers.api.util.constants.PlatformConstants.NAME_LENGTH;

/**
 * The platform's model entity.
 */
@Entity
@Table(name = "platforms")
@Getter
@Setter
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id")
    private Integer id;

    @Column(nullable = false, length = NAME_LENGTH, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        isAvailable = true;
        createdAt = updatedAt = LocalDateTime.now(ZoneId.of(LOCALE_ZONE));
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now(ZoneId.of(LOCALE_ZONE));
    }

}
