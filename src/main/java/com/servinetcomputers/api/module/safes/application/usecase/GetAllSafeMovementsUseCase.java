package com.servinetcomputers.api.module.safes.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeMovementDto;

import java.util.List;

/**
 * Get all movements from a safe.
 * <p>Receives the {@code Safe ID}.</p>
 * <p>Returns the {@link SafeMovementDto} list.</p>
 */
public interface GetAllSafeMovementsUseCase extends UseCase<List<SafeMovementDto>, Integer> {
}
