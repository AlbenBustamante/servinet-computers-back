package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CreateCashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.MyCashRegistersReports;

public interface CreateCashRegisterDetailUseCase extends UseCase<MyCashRegistersReports, CreateCashRegisterDetailDto> {
}
