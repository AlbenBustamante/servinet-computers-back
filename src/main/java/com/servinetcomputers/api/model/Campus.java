package com.servinetcomputers.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.servinetcomputers.api.util.constants.CampusConstants.ADDRESS_LENGTH;
import static com.servinetcomputers.api.util.constants.CampusConstants.CELLPHONE_LENGTH;
import static com.servinetcomputers.api.util.constants.CampusConstants.TERMINAL_LENGTH;
import static com.servinetcomputers.api.util.constants.LocalConstants.DEFAULT_ZONE;

/**
 * The campus' model entity.
 */
@Entity
@Table(name = "campuses")
@Getter
@Setter
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campus_id")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(nullable = false, unique = true)
    private Integer numeral;

    @Column(nullable = false, length = ADDRESS_LENGTH, unique = true)
    private String address;

    @Column(length = CELLPHONE_LENGTH, unique = true)
    private String cellphone;

    @Column(nullable = false, length = TERMINAL_LENGTH, unique = true)
    private String terminal;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "campuses_platforms",
            joinColumns = {@JoinColumn(name = "campus_id")},
            inverseJoinColumns = {@JoinColumn(name = "platform_id")}
    )
    private Set<Platform> platforms;

    /**
     * Add a platform to the campus' collection.
     *
     * @param platform the platform to be added.
     */
    public void addPlatform(Platform platform) {
        platforms.add(platform);
    }

    /**
     * Remove a platform from the campus' collection.
     *
     * @param platform the platform to be removed.
     */
    public void removePlatform(Platform platform) {
        platforms.remove(platform);
    }

    @PrePersist
    public void prePersist() {
        platforms = new HashSet<>();
        isAvailable = true;
        createdAt = updatedAt = LocalDateTime.now(DEFAULT_ZONE);
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now(DEFAULT_ZONE);
    }

}
