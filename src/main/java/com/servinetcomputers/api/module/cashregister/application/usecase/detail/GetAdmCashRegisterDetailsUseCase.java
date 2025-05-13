package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.cashregister.domain.dto.AdmCashRegistersDto;

/**
 * Gets a cash register details list by the day, including pending to close, and cash registers without be used recently.
 *
 * <p>Returns a {@link AdmCashRegistersDto} object.</p>
 */
public interface GetAdmCashRegisterDetailsUseCase extends UseCaseWithoutParam<AdmCashRegistersDto> {
}
