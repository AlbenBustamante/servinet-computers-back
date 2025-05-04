package com.servinetcomputers.api.module.safes.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDto;

import java.util.List;

public interface GetAllSafesUseCase extends UseCaseWithoutParam<List<SafeDto>> {
}
