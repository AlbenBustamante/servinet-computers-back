package com.servinetcomputers.api.module.auth.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.user.domain.dto.UserRequest;
import com.servinetcomputers.api.module.user.domain.dto.UserResponse;

public interface CreateUserUseCase extends UseCase<UserResponse, UserRequest> {
}
