package com.servinetcomputers.api.module.user.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.JourneyDetailDto;

import java.time.YearMonth;

/**
 * Obtiene todas las jornadas de trabajo realizadas según el ID de un usuario y el mes de consulta.
 * <p>Recibe el {@code ID} del usuario.</p>
 * <p>Recibe un {@link YearMonth} para realizar la consulta por mes y año.</p>
 * <p>Devuelve un {@link JourneyDetailDto} con los detalles del mes.</p>
 */
public interface GetJourneysUseCase extends UseCaseBiParam<JourneyDetailDto, Integer, YearMonth> {
}
