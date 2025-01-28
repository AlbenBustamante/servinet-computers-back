package com.servinetcomputers.api.domain.auth.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.domain.user.domain.dto.UserRequest;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;

public interface CreateUserUseCase extends UseCase<UserResponse, UserRequest> {
}
