package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.EndBreakUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EndBreakService implements EndBreakUseCase {
    private final CashRegisterDetailPersistenceAdapter repository;
    private final DateTimeService dateTimeService;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public CashRegisterDetailDto call(Integer param) {
        final var cashRegisterDetail = repository.get(param)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento: #" + param));

        cashRegisterDetail.setFinalBreak(dateTimeService.now());
        cashRegisterDetail.setStatus(CashRegisterDetailStatus.WORKING);

        return repository.save(cashRegisterDetail);
    }
}
