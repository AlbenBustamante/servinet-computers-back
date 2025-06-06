package com.servinetcomputers.api.core.audit.infra;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

import static com.servinetcomputers.api.core.util.constants.UserConstants.CODE_LENGTH;

/**
 * Modelo base para realizar auditoría de las entidades.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AuditableEntity {
    /**
     * Determina si el registro está habilitado.
     */
    @Column(nullable = false)
    private Boolean enabled; // TODO: Cambiar a "deleted" o "LocalDateTime deletedDate".

    /**
     * Fecha y hora exacta del registro.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /**
     * Fecha y hora exacta de la última actualización del registro.
     */
    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    /**
     * Código del usuario que creó el registro.
     */
    @Column(length = CODE_LENGTH)
    @CreatedBy
    private String createdBy;

    /**
     * Código del último usuario que actualizó el registro.
     */
    @Column(length = CODE_LENGTH)
    @LastModifiedBy
    private String modifiedBy;
}
