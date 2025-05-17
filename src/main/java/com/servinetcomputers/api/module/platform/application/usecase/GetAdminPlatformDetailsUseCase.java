package com.servinetcomputers.api.module.platform.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.platform.domain.dto.AdminPlatformDto;

import java.time.YearMonth;

/**
 * Loads platform, balances and transfers in a month.
 * <p>Receives the {@code Platform ID}.</p>
 * <p>Receives a {@link YearMonth} for month fetching.</p>
 */
public interface GetAdminPlatformDetailsUseCase extends UseCaseBiParam<AdminPlatformDto, Integer, YearMonth> {
}
