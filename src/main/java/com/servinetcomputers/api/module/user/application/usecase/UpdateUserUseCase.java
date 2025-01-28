package com.servinetcomputers.api.module.user.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.user.domain.dto.UpdateUserDto;
import com.servinetcomputers.api.module.user.domain.dto.UserResponse;

public interface UpdateUserUseCase extends UseCaseBiParam<UserResponse, Integer, UpdateUserDto> {
}
