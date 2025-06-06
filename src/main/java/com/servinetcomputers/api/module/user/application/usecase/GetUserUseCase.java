package com.servinetcomputers.api.module.user.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserDto;

public interface GetUserUseCase extends UseCase<UserDto, Integer> {
}
