package com.servinetcomputers.api.domain.safes.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeResponse;

import java.util.List;

public interface GetAllSafesUseCase extends UseCaseWithoutParam<List<SafeResponse>> {
}
