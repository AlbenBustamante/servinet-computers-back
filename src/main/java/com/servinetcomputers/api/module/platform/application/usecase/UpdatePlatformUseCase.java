package com.servinetcomputers.api.module.platform.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.UpdatePlatformDto;

/**
 * Update an existing and available platform.
 */
public interface UpdatePlatformUseCase extends UseCaseBiParam<PlatformDto, Integer, UpdatePlatformDto> {
}
