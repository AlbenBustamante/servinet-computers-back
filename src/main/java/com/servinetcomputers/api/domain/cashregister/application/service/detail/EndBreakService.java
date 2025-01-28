package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.EndBreakUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EndBreakService implements EndBreakUseCase {
    private final CashRegisterDetailRepository repository;
    private final DateTimeService dateTimeService;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public CashRegisterDetailResponse call(Integer param) {
        final var cashRegisterDetail = repository.get(param)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento: #" + param));

        cashRegisterDetail.setFinalBreak(dateTimeService.timeNow());
        cashRegisterDetail.setStatus(CashRegisterDetailStatus.WORKING);

        return repository.save(cashRegisterDetail);
    }
}
