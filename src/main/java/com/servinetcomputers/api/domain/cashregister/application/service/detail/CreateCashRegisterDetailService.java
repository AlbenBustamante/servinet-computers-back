package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.CreateCashRegisterDetailUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailRequest;
import com.servinetcomputers.api.domain.cashregister.domain.dto.MyCashRegistersReports;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateCashRegisterDetailService implements CreateCashRegisterDetailUseCase {
    private final CashRegisterDetailRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public MyCashRegistersReports call(CashRegisterDetailRequest param) {
        return repository.create(param);
    }
}
