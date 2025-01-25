package com.servinetcomputers.api.domain.cashregister.application.usecase;

import com.servinetcomputers.api.domain.UseCaseBiParam;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterResponse;
import com.servinetcomputers.api.domain.cashregister.domain.dto.UpdateCashRegisterDto;

public interface UpdateCashRegisterUseCase extends UseCaseBiParam<CashRegisterResponse, Integer, UpdateCashRegisterDto> {
}
