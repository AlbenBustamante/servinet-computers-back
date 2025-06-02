package com.servinetcomputers.api.module.safes.application.port.in;

import com.servinetcomputers.api.module.safes.application.port.in.command.CreateAdminTransferCommand;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;

/**
 * Suma o resta la base de una caja fuerte según el ID de su movimiento.
 */
public interface CreateAdminTransferUseCase {
    /**
     * Suma o resta la base de una caja fuerte según el ID de su movimiento.
     *
     * @param safeDetailId {@code ID} del movimiento.
     * @param command      Comando con la data.
     * @return el movimiento actualizado de la caja fuerte.
     */
    SafeDetailDto create(Integer safeDetailId, CreateAdminTransferCommand command);
}
