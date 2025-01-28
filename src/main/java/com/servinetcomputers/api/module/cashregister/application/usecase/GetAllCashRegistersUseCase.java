package com.servinetcomputers.api.module.cashregister.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterResponse;

import java.util.List;

public interface GetAllCashRegistersUseCase extends UseCaseWithoutParam<List<CashRegisterResponse>> {
}
