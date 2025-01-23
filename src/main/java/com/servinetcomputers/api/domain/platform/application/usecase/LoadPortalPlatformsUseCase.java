package com.servinetcomputers.api.domain.platform.application.usecase;

import com.servinetcomputers.api.domain.UseCaseWithoutParam;
import com.servinetcomputers.api.domain.platform.domain.dto.PortalPlatformDto;

import java.util.List;

/**
 * Get all the available portal platforms.
 */
public interface LoadPortalPlatformsUseCase extends UseCaseWithoutParam<List<PortalPlatformDto>> {
}
