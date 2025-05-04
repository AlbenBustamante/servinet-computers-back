package com.servinetcomputers.api.module.platform.application.usecase.transfer;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformTransferWithVouchersDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create and persist a new transfer, and upload vouchers to a cloud.
 */
public interface CreatePlatformTransferUseCase extends UseCaseBiParam<PlatformTransferDto, CreatePlatformTransferWithVouchersDto, MultipartFile[]> {
}
