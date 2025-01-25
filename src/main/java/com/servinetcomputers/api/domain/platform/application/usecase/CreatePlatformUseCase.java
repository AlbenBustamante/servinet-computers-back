package com.servinetcomputers.api.domain.platform.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformResponse;

/**
 * Create and persist a new platform.
 */
public interface CreatePlatformUseCase extends UseCase<PlatformResponse, PlatformRequest> {
}
