package com.servinetcomputers.api.module.passwordtempcode.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.PasswordTempCodeDto;

/**
 * Checks if already exists an unused code.
 * <p>If not, persists the code.</p>
 * <p>Otherwise, generates and persists a new code.</p>
 * <p>Returns a {@link PasswordTempCodeDto} data.</p>
 */
public interface CreatePasswordTempCodeUseCase extends UseCaseWithoutParam<PasswordTempCodeDto> {
}
