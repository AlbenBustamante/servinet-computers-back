package com.servinetcomputers.api.module.safes.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeMovementDto;

/**
 * Get all movements from a Safe Detail.
 * <p>Receives the {@code Safe Detail ID}.</p>
 * <p>Returns the movements as a {@link SafeMovementDto}</p>
 */
public interface GetSafeDetailMovementsByIdUseCase extends UseCase<SafeMovementDto, Integer> {
}
