package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;

import java.util.List;

public interface GetCashTransfersByIdUseCase extends UseCase<List<CashTransferDto>, Integer> {
}
