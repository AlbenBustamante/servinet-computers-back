package com.servinetcomputers.api.module.platform.application.usecase.transfer;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;

/**
 * Search an existing and available transfer.
 */
public interface GetPlatformTransferUseCase extends UseCase<PlatformTransferDto, Integer> {
}
