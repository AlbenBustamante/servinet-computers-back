package com.servinetcomputers.api.domain.user.application.usecase;

import com.servinetcomputers.api.domain.UseCase;
import com.servinetcomputers.api.domain.user.domain.dto.UserRequest;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;

public interface CreateUserUseCase extends UseCase<UserResponse, UserRequest> {
}
