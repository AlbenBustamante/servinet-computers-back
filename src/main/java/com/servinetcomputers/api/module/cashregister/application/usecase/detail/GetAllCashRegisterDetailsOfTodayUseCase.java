package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;

import java.util.List;

public interface GetAllCashRegisterDetailsOfTodayUseCase extends UseCaseWithoutParam<List<CashRegisterDetailResponse>> {
}
