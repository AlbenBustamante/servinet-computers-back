package com.servinetcomputers.api.module.tempcode.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeResponse;

/**
 * Loads/generates temp code with some validations:
 * <p>1. Gets the last generated code.
 * <p>2. Check if the code is empty or the {@code usedBy} is not null.</p>
 * <p>3. If it's true, generate a new temp code.</p>
 * <p>4. Otherwise, returns it.</p>
 */
public interface LoadTempCodeUseCase extends UseCaseWithoutParam<TempCodeResponse> {
}
