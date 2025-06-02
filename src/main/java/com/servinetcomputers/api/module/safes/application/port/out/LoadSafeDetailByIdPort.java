package com.servinetcomputers.api.module.safes.application.port.out;

import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;

/**
 * Carga un movimiento de caja fuerte según el ID del movimiento.
 */
public interface LoadSafeDetailByIdPort {
    /**
     * Carga un movimiento de caja fuerte según el ID del movimiento.
     *
     * @param safeDetailId {@code ID} del movimiento a buscar.
     * @return el movimiento encontrado.
     */
    SafeDetailDto load(Integer safeDetailId);
}
