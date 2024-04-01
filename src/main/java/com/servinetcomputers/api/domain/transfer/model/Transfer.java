package com.servinetcomputers.api.domain.transfer.model;

import com.servinetcomputers.api.domain.platform.model.Platform;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.servinetcomputers.api.util.LocalConstants.DEFAULT_ZONE;

/**
 * The transfer's model entity.
 */
@Entity
@Table(name = "transfers")
@Setter
@Getter
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Integer id;

    @Column(name = "platform_id", nullable = false)
    private Integer platformId;

    @Column(name = "campus_id", nullable = false)
    private Integer campusId;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "platform_id", insertable = false, updatable = false)
    private Platform platform;

    @PrePersist
    public void prePersist() {
        isAvailable = true;
        createdAt = updatedAt = LocalDateTime.now(DEFAULT_ZONE);
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now(DEFAULT_ZONE);
    }

}
