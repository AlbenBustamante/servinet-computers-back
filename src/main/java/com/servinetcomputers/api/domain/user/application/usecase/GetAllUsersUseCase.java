package com.servinetcomputers.api.domain.user.application.usecase;

import com.servinetcomputers.api.domain.UseCaseWithoutParam;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;

import java.util.List;

public interface GetAllUsersUseCase extends UseCaseWithoutParam<List<UserResponse>> {
}
