package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.DeleteCashRegisterDetailUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteCashRegisterDetailService implements DeleteCashRegisterDetailUseCase {
    private final CashRegisterDetailRepository repository;
    
    @Transactional(rollbackFor = AppException.class)
    @Override
    public Boolean call(Integer param) {
        return repository.delete(param);
    }
}
