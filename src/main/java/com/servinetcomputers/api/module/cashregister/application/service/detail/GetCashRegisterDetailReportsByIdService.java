package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashRegisterDetailReportsByIdUseCase;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter1;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.module.reports.application.usecase.GetCashRegisterDetailReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetCashRegisterDetailReportsByIdService implements GetCashRegisterDetailReportsByIdUseCase {
    private final CashRegisterDetailPersistenceAdapter1 repository;
    private final GetCashRegisterDetailReportsUseCase getCashRegisterDetailReportsUseCase;

    @Transactional(readOnly = true)
    @Override
    public CashRegisterDetailReportsDto call(Integer cashRegisterDetailId) {
        final var cashRegisterDetail = repository.get(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento: #" + cashRegisterDetailId));

        return getCashRegisterDetailReportsUseCase.call(cashRegisterDetail);
    }
}
