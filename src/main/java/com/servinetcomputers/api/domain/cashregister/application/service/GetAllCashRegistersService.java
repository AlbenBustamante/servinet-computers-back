package com.servinetcomputers.api.domain.cashregister.application.service;

import com.servinetcomputers.api.domain.cashregister.application.usecase.GetAllCashRegistersUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterResponse;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllCashRegistersService implements GetAllCashRegistersUseCase {
    private final CashRegisterRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<CashRegisterResponse> call() {
        return repository.getAll();
    }
}
