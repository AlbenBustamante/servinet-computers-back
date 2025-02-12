package com.servinetcomputers.api.module.cashtransfer.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;

public interface CreateCashTransferUseCase extends UseCase<CashTransferDto, CreateCashTransferDto> {
}
