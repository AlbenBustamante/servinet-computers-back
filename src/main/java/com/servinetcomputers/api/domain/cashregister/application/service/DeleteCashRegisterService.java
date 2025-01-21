package com.servinetcomputers.api.domain.cashregister.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.cashregister.application.usecase.DeleteCashRegisterUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class DeleteCashRegisterService implements DeleteCashRegisterUseCase {
    private final CashRegisterRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public Boolean call(Integer param) {
        return repository.delete(param);
    }
}
