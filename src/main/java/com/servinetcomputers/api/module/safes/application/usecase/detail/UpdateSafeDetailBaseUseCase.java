package com.servinetcomputers.api.module.safes.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.module.safes.persistence.entity.SafeBase;

/**
 * If the initial base is by default, then update the initial and final base.
 * <p>If not, then update only the final base.</p>
 * <p>Also, create a new {@link SafeBase} record.</p>
 */
public interface UpdateSafeDetailBaseUseCase extends UseCaseBiParam<SafeDetailResponse, Integer, BaseDto> {
}
