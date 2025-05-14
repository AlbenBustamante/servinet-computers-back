package com.servinetcomputers.api.module.auth.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutReturn;
import com.servinetcomputers.api.module.auth.dto.RequestChangePasswordDto;

/**
 * Checks the code received by user and sends an email with a temp code.
 * <p>Receives the {@link RequestChangePasswordDto} with {@code userCode}.</p>
 */
public interface SendCodeToChangePasswordUseCase extends UseCaseWithoutReturn<RequestChangePasswordDto> {
}
