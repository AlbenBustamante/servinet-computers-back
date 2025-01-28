package com.servinetcomputers.api.module.safes.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeResponse;

public interface CreateSafeUseCase extends UseCase<SafeResponse, SafeRequest> {
}
