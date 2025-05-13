package com.servinetcomputers.api.module.user.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;

import java.util.List;

public interface GetAllUsersUseCase extends UseCaseWithoutParam<List<UserDto>> {
}
