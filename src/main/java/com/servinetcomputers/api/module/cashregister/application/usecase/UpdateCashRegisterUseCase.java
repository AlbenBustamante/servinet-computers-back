package com.servinetcomputers.api.module.cashregister.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterResponse;
import com.servinetcomputers.api.module.cashregister.domain.dto.UpdateCashRegisterDto;

public interface UpdateCashRegisterUseCase extends UseCaseBiParam<CashRegisterResponse, Integer, UpdateCashRegisterDto> {
}
