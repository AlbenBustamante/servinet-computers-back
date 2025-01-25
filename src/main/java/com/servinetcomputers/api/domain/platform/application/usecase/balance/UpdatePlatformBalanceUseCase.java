package com.servinetcomputers.api.domain.platform.application.usecase.balance;

import com.servinetcomputers.api.domain.UseCaseBiParam;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.UpdatePlatformBalanceDto;

public interface UpdatePlatformBalanceUseCase extends UseCaseBiParam<PlatformBalanceResponse, Integer, UpdatePlatformBalanceDto> {
}
