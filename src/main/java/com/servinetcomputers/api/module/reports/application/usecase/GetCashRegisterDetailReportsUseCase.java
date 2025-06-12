package com.servinetcomputers.api.module.reports.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailReportsDto;

public interface GetCashRegisterDetailReportsUseCase extends UseCase<CashRegisterDetailReportsDto, CashRegisterDetailDto> {
}
