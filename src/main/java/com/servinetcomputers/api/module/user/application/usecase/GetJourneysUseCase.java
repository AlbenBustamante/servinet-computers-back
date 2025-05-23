package com.servinetcomputers.api.module.user.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;

import java.time.YearMonth;
import java.util.List;

/**
 * Obtiene todas las jornadas de trabajo realizadas según el ID de un usuario y el mes de consulta.
 * <p>Recibe el {@code ID} del usuario.</p>
 * <p>Recibe un {@link YearMonth} para realizar la consulta por mes y año.</p>
 * <p>Devuelve un listado de jornadas {@link CashRegisterDetailDto}.</p>
 */
public interface GetJourneysUseCase extends UseCaseBiParam<List<CashRegisterDetailDto>, Integer, YearMonth> {
}
