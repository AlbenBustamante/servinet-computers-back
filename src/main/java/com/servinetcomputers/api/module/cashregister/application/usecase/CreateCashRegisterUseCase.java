package com.servinetcomputers.api.module.cashregister.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterRequest;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterResponse;

public interface CreateCashRegisterUseCase extends UseCase<CashRegisterResponse, CashRegisterRequest> {
}
