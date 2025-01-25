package com.servinetcomputers.api.domain.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;

import java.util.List;

public interface GetAllCashRegisterDetailsOfTodayUseCase extends UseCaseWithoutParam<List<CashRegisterDetailResponse>> {
}
