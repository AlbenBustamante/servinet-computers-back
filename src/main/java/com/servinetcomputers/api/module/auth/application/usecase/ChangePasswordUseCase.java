package com.servinetcomputers.api.module.auth.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutReturn;
import com.servinetcomputers.api.module.auth.dto.ChangePasswordDto;

/**
 * Checks the temp code and user code.
 * <p>If the codes are ok, proceeds to check the passwords.</p>
 * <p>If the passwords do matches, the password will be encoded and updated.</p>
 * <p>Receives a {@link ChangePasswordDto} data.</p>
 */
public interface ChangePasswordUseCase extends UseCaseWithoutReturn<ChangePasswordDto> {
}
