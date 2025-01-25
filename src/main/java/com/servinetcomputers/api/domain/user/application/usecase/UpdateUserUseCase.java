package com.servinetcomputers.api.domain.user.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.domain.user.domain.dto.UpdateUserDto;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;

public interface UpdateUserUseCase extends UseCaseBiParam<UserResponse, Integer, UpdateUserDto> {
}
