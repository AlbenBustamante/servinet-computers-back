package com.servinetcomputers.api.domain.platform.application.usecase;

import com.servinetcomputers.api.domain.UseCaseBiParam;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.UpdatePlatformDto;

/**
 * Update an existing and available platform.
 */
public interface UpdatePlatformUseCase extends UseCaseBiParam<PlatformResponse, Integer, UpdatePlatformDto> {
}
