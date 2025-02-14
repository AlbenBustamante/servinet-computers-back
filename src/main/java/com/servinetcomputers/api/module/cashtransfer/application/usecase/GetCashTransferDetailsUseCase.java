package com.servinetcomputers.api.module.cashtransfer.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;

public interface GetCashTransferDetailsUseCase extends UseCaseBiParam<CashTransferDto, CashTransferDto, Integer> {
}
