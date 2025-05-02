package com.servinetcomputers.api.module.platform.application.usecase.balance;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.UpdatePlatformBalanceDto;

public interface UpdatePlatformBalanceUseCase extends UseCaseBiParam<PlatformBalanceDto, Integer, UpdatePlatformBalanceDto> {
}
