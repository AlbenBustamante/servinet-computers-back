package com.servinetcomputers.api.module.user.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UpdateUserDto;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserDto;

public interface UpdateUserUseCase extends UseCaseBiParam<UserDto, Integer, UpdateUserDto> {
}
