package com.servinetcomputers.api.module.platform.application.usecase.transfer;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;
import com.servinetcomputers.api.module.platform.domain.dto.UpdatePlatformTransferDto;

/**
 * Update an existing and available transfer.
 */
public interface UpdatePlatformTransferUseCase extends UseCaseBiParam<PlatformTransferDto, Integer, UpdatePlatformTransferDto> {
}
