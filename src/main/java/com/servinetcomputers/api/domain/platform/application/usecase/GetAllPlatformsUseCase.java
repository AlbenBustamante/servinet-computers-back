package com.servinetcomputers.api.domain.platform.application.usecase;

import com.servinetcomputers.api.domain.UseCaseWithoutParam;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformResponse;

import java.util.List;

/**
 * Get all the available platforms.
 */
public interface GetAllPlatformsUseCase extends UseCaseWithoutParam<List<PlatformResponse>> {
}
