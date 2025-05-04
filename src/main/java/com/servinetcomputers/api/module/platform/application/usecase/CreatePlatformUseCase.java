package com.servinetcomputers.api.module.platform.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformDto;

/**
 * Create and persist a new platform.
 */
public interface CreatePlatformUseCase extends UseCase<PlatformDto, CreatePlatformDto> {
}
