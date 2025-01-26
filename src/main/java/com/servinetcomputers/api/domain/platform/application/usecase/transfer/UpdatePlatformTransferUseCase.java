package com.servinetcomputers.api.domain.platform.application.usecase.transfer;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.UpdatePlatformTransferDto;

/**
 * Update an existing and available transfer.
 */
public interface UpdatePlatformTransferUseCase extends UseCaseBiParam<PlatformTransferResponse, Integer, UpdatePlatformTransferDto> {
}
