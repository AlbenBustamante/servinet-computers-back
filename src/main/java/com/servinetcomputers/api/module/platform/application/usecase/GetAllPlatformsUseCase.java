package com.servinetcomputers.api.module.platform.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformResponse;

import java.util.List;

/**
 * Get all the available platforms.
 */
public interface GetAllPlatformsUseCase extends UseCaseWithoutParam<List<PlatformResponse>> {
}
