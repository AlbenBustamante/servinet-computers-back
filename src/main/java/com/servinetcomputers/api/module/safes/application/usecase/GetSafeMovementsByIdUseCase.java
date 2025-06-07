package com.servinetcomputers.api.module.safes.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;

import java.time.LocalDate;

/**
 * Carga los detalles de una caja fuerte según su {@code ID} y el día seleccionado.
 * <p>En dado caso que no encuentre detalles, serán creados.</p>
 *
 * <p>Recibe el {@code ID} de la caja fuerte.</p>
 * <p>Recibe la fecha {@link LocalDate} para realizar la consulta.</p>
 * <p>Devuelve un {@link SafeDetailDto} con los detalles encontrados de la caja fuerte.</p>
 */
public interface GetSafeMovementsByIdUseCase extends UseCaseBiParam<SafeDetailDto, Integer, LocalDate> {
}
