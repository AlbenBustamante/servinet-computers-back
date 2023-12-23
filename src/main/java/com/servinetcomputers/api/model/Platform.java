package com.servinetcomputers.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.servinetcomputers.api.util.constants.LocalConstants.DEFAULT_ZONE;
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

    @OneToMany(mappedBy = "platform")
    private Set<Transfer> transfers;

    @PrePersist
    public void prePersist() {
        transfers = new HashSet<>();
        isAvailable = true;
        createdAt = updatedAt = LocalDateTime.now(DEFAULT_ZONE);
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now(DEFAULT_ZONE);
    }

}
