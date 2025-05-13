package com.servinetcomputers.api.module.safes.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;

import java.util.List;

/**
 * Do a safe details search of the current day and return it if found data.
 * <p>Otherwise, search the last registered base by the safe ID and create a new detail for the day.</p>
 * <p>And finally, if not found any base, sets a zero base and create a new detail for the day.</p>
 */
public interface LoadSafeDetailsOfDayUseCase extends UseCaseWithoutParam<List<SafeDetailDto>> {
}
