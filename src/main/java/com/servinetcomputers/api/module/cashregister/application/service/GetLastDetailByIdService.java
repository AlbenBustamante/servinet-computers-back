package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.module.cashregister.application.usecase.GetLastDetailByIdUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GetLastDetailByIdService implements GetLastDetailByIdUseCase {
    private final CashRegisterDetailPersistenceAdapter adapter;

    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public CashRegisterDetailDto call(Integer cashRegisterId) {
        return adapter.getLatestByCashRegisterId(cashRegisterId);
    }
}
