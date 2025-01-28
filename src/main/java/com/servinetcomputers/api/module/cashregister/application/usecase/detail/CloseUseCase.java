package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailReportsDto;

public interface CloseUseCase extends UseCaseBiParam<CashRegisterDetailReportsDto, Integer, BaseDto> {
}
