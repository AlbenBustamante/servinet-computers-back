package com.servinetcomputers.api.domain.reports.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;

public interface GetCashRegisterDetailReportsUseCase extends UseCase<CashRegisterDetailReportsDto, CashRegisterDetailResponse> {
}
