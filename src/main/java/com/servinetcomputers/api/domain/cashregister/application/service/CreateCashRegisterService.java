package com.servinetcomputers.api.domain.cashregister.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.cashregister.application.usecase.CreateCashRegisterUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterResponse;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class CreateCashRegisterService implements CreateCashRegisterUseCase {
    private final CashRegisterRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public CashRegisterResponse call(CashRegisterRequest request) {
        return repository.create(request);
    }
}
