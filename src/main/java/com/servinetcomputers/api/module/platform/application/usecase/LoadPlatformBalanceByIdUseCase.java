package com.servinetcomputers.api.module.platform.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;

import java.time.LocalDate;

/**
 * Carga el saldo más reciente de una plataforma.
 * <p>Recibe el {@code ID} de la plataforma.</p>
 * <p>Recibe un {@link LocalDate}.</p>
 * <p>Devuelve un {@link PlatformBalanceDto}.</p>
 */
public interface LoadPlatformBalanceByIdUseCase extends UseCaseBiParam<PlatformBalanceDto, Integer, LocalDate> {
}
