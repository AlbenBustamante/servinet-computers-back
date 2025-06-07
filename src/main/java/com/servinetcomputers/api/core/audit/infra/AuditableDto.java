package com.servinetcomputers.api.core.audit.infra;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Clase abstracta para los modelos DTO que contengan datos de auditoría y soft-delete.
 */
@Getter
@Setter
@ToString
public abstract class AuditableDto {
    private Boolean enabled;
    private String createdBy, modifiedBy;
    private LocalDateTime createdDate, modifiedDate;
}
