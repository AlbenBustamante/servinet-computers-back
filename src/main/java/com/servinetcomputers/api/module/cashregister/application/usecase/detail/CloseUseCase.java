package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CloseCashRegisterDetailDto;

public interface CloseUseCase extends UseCaseBiParam<CashRegisterDetailReportsDto, Integer, CloseCashRegisterDetailDto> {
}
