package com.servinetcomputers.api.module.platform.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformStatsDto;

import java.time.LocalDate;

/**
 * Obtiene todas las estadísticas de una plataforma según el {@link PlatformBalanceDto} dado y una fecha dada.
 * <p>Recibe un {@link PlatformBalanceDto}.</p>
 * <p>Recibe un {@link LocalDate}.</p>
 * <p>Devuelve un {@link PlatformStatsDto} con las estadísticas.</p>
 */
public interface GetPlatformStatsByBalanceUseCase extends UseCaseBiParam<PlatformStatsDto, PlatformBalanceDto, LocalDate> {
}
