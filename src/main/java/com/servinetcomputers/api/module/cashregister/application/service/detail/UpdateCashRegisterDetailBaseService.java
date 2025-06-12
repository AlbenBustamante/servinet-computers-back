package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.UpdateCashRegisterDetailBaseUseCase;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter1;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.UpdateCashRegisterDetailBaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateCashRegisterDetailBaseService implements UpdateCashRegisterDetailBaseUseCase {
    private final CashRegisterDetailPersistenceAdapter1 repository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public CashRegisterDetailDto call(Integer cashRegisterDetailId, UpdateCashRegisterDetailBaseDto dto) {
        final var cashRegisterDetail = repository.get(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada #" + cashRegisterDetailId));

        if (dto.initial()) {
            cashRegisterDetail.setDetailInitialBase(dto.base());
        } else {
            cashRegisterDetail.setDetailFinalBase(dto.base());
        }

        return repository.save(cashRegisterDetail);
    }
}
