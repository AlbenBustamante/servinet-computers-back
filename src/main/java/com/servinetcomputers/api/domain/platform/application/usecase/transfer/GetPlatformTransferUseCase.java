package com.servinetcomputers.api.domain.platform.application.usecase.transfer;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferResponse;

/**
 * Search an existing and available transfer.
 */
public interface GetPlatformTransferUseCase extends UseCase<PlatformTransferResponse, Integer> {
}
