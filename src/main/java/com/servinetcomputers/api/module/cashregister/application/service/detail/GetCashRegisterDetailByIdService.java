package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashRegisterDetailByIdUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetCashRegisterDetailByIdService implements GetCashRegisterDetailByIdUseCase {
    private final CashRegisterDetailRepository repository;

    @Transactional(readOnly = true)
    @Override
    public CashRegisterDetailDto call(Integer param) {
        return repository.get(param)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento: #" + param));
    }
}
