package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.cashregister.domain.dto.AdmCashRegistersDto;

/**
 * Get all the current cash register details of the day.
 *
 * <p>But, if a cash register is actually occupied, it will be loaded,</p>
 */
public interface GetAllCashRegisterDetailsOfTodayUseCase extends UseCaseWithoutParam<AdmCashRegistersDto> {
}
