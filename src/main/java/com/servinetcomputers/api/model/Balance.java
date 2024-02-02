package com.servinetcomputers.api.model;

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

import static com.servinetcomputers.api.util.constants.LocalConstants.DEFAULT_ZONE;

/**
 * The balance's model entity.
 */
@Entity
@Table(name = "balances")
@Getter
@Setter
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private Integer id;

    @Column(nullable = false)
    private Integer platformId;

    @Column(nullable = false)
    private Integer campusId;

    @Column(nullable = false)
    private BigDecimal initialBalance;

    @Column(nullable = false)
    private BigDecimal finalBalance;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "platform_id", updatable = false, insertable = false)
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "campus_id", updatable = false, insertable = false)
    private Campus campus;

    @PrePersist
    public void prePersist() {
        if (finalBalance == null) {
            finalBalance = BigDecimal.ZERO;
        }

        isAvailable = true;
        createdAt = updatedAt = LocalDateTime.now(DEFAULT_ZONE);
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now(DEFAULT_ZONE);
    }

}
