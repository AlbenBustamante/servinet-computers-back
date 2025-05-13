package com.servinetcomputers.api.module.auth.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.user.domain.dto.CreateUserDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;

public interface CreateUserUseCase extends UseCase<UserDto, CreateUserDto> {
}
