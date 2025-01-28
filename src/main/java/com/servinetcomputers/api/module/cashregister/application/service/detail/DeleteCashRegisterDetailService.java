package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.DeleteCashRegisterDetailUseCase;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteCashRegisterDetailService implements DeleteCashRegisterDetailUseCase {
    private final CashRegisterDetailRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public void call(Integer param) {
        final var cashRegisterDetail = repository.get(param)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja del día: #" + param));

        cashRegisterDetail.setStatus(CashRegisterDetailStatus.CLOSED);
        cashRegisterDetail.setEnabled(false);

        repository.save(cashRegisterDetail);
    }
}
