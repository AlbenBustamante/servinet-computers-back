package com.servinetcomputers.api.domain.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;

public interface CloseUseCase extends UseCaseBiParam<CashRegisterDetailReportsDto, Integer, BaseDto> {
}
