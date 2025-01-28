package com.servinetcomputers.api.module.auth.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.auth.dto.AuthRequest;
import com.servinetcomputers.api.module.auth.dto.AuthResponse;

/**
 * Login for users and campuses.
 */
public interface LoginUseCase extends UseCase<AuthResponse, AuthRequest> {
}
