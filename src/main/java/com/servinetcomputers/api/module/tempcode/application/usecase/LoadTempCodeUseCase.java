package com.servinetcomputers.api.module.tempcode.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeResponse;

/**
 * Loads/generates temp code with some validations:
 * <p>1. Check if already exists a temp code by the current day.</p>
 * <p>2. Check if the {@code usedBy} is null.</p>
 * <p>3. If is true, returns the same temp code.</p>
 * <p>4. If is false, generate a new temp code.</p>
 */
public interface LoadTempCodeUseCase extends UseCaseWithoutParam<TempCodeResponse> {
}
