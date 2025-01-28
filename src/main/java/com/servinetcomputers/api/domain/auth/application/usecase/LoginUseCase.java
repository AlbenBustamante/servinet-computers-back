package com.servinetcomputers.api.domain.auth.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.domain.auth.dto.AuthRequest;
import com.servinetcomputers.api.domain.auth.dto.AuthResponse;

/**
 * Login for users and campuses.
 */
public interface LoginUseCase extends UseCase<AuthResponse, AuthRequest> {
}
