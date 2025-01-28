package com.servinetcomputers.api.module.platform.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.platform.domain.dto.PortalPlatformDto;

import java.util.List;

/**
 * Get all the available portal platforms.
 */
public interface LoadPortalPlatformsUseCase extends UseCaseWithoutParam<List<PortalPlatformDto>> {
}
