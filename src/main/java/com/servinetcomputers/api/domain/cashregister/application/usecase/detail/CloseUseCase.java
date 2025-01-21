package com.servinetcomputers.api.domain.cashregister.application.usecase.detail;

import com.servinetcomputers.api.domain.UseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CloseCashRegisterDetailDto;

public interface CloseUseCase extends UseCase<CashRegisterDetailReportsDto, CloseCashRegisterDetailDto> {
}
