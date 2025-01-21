package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.CashRegisterDetailAlreadyExistsUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.AlreadyExistsCashRegisterDetailDto;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.CASHIER_AUTHORITY;
import static com.servinetcomputers.api.core.security.util.SecurityConstants.SUPERVISOR_AUTHORITY;

@RequiredArgsConstructor
@Service
public class CashRegisterDetailAlreadyExistsService implements CashRegisterDetailAlreadyExistsUseCase {
    private final CashRegisterDetailRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = {CASHIER_AUTHORITY, SUPERVISOR_AUTHORITY})
    @Override
    public AlreadyExistsCashRegisterDetailDto call() {
        return repository.alreadyExists();
    }
}
