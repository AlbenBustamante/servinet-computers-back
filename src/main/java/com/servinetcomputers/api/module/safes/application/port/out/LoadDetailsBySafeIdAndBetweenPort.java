package com.servinetcomputers.api.module.safes.application.port.out;

import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Carga los detalles de una caja fuerte según su {@code ID} y un rango de fechas.
 */
public interface LoadDetailsBySafeIdAndBetweenPort {
    /**
     * Carga los detalles de una caja fuerte según su {@code ID} y un rango de fechas.
     *
     * @param safeId    El {@code ID} de la caja fuerte.
     * @param endDate   La fecha inicial para la consulta.
     * @param startDate La fecha final para la consulta.
     * @return Devuelve un listado de detalles.
     */
    List<SafeDetailDto> load(Integer safeId, LocalDateTime startDate, LocalDateTime endDate);
}
