package com.servinetcomputers.api.module.reports.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;

public interface GetCashRegisterDetailReportsUseCase extends UseCase<CashRegisterDetailReportsDto, CashRegisterDetailResponse> {
}
