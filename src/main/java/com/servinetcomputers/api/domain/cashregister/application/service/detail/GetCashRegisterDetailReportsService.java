package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.GetCashRegisterDetailReportsUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetCashRegisterDetailReportsService implements GetCashRegisterDetailReportsUseCase {
    private final CashRegisterDetailRepository repository;

    @Transactional(readOnly = true)
    @Override
    public CashRegisterDetailReportsDto call(Integer param) {
        return repository.getCashRegisterDetailReports(param);
    }
}
