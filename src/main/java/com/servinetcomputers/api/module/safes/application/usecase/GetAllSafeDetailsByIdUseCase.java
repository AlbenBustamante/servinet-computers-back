package com.servinetcomputers.api.module.safes.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;

import java.util.List;

/**
 * Get all details from a safe.
 * <p>Receives the {@code Safe ID}.</p>
 * <p>Returns the {@link SafeDetailDto} list.</p>
 */
public interface GetAllSafeDetailsByIdUseCase extends UseCase<List<SafeDetailDto>, Integer> {
}
