package com.servinetcomputers.api.core.audit.infra;

import java.time.LocalDateTime;

/**
 * Modelo de dominio para auditoría.
 *
 * @param createdBy    código del usuario que creó el registro.
 * @param createdDate  fecha y hora exacta del registro.
 * @param modifiedBy   código del último usuario que actualizó el registro.
 * @param modifiedDate fecha y hora exacta de la última actualización del registro.
 * @param enabled      {@code true} si está habilitado el registro.
 */
public record Auditable(
        String createdBy,
        LocalDateTime createdDate,
        String modifiedBy,
        LocalDateTime modifiedDate,
        Boolean enabled
) {
    /**
     * Ajusta la propiedad {@code enabled} para sea filtrado como eliminado.
     *
     * @return el nuevo {@link Auditable}.
     */
    public Auditable delete() {
        return new Auditable(
                createdBy,
                createdDate,
                modifiedBy,
                modifiedDate,
                false
        );
    }
}
