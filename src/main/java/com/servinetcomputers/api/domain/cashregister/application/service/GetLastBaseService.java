package com.servinetcomputers.api.domain.cashregister.application.service;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.application.usecase.GetLastBaseUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetLastBaseService implements GetLastBaseUseCase {
    private final CashRegisterRepository repository;

    @Transactional(readOnly = true)
    @Override
    public BaseDto call(Integer param) {
        return repository.getLastFinalBaseFromCashRegisterId(param);
    }
}
