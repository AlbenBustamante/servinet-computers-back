package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.GetCashRegisterDetailByIdUseCase;
import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.GetCashRegisterDetailReportsByIdUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.domain.reports.application.usecase.GetCashRegisterDetailReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetCashRegisterDetailReportsByIdService implements GetCashRegisterDetailReportsByIdUseCase {
    private final GetCashRegisterDetailReportsUseCase getCashRegisterDetailReportsUseCase;
    private final GetCashRegisterDetailByIdUseCase getCashRegisterDetailByIdUseCase;

    @Transactional(readOnly = true)
    @Override
    public CashRegisterDetailReportsDto call(Integer param) {
        final var cashRegisterDetail = getCashRegisterDetailByIdUseCase.call(param);
        return getCashRegisterDetailReportsUseCase.call(cashRegisterDetail);
    }
}
