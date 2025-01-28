package com.servinetcomputers.api.module.platform.application.usecase.transfer;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferRequest;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create and persist a new transfer, and upload vouchers to a cloud.
 */
public interface CreatePlatformTransferUseCase extends UseCaseBiParam<PlatformTransferResponse, PlatformTransferRequest, MultipartFile[]> {
}
