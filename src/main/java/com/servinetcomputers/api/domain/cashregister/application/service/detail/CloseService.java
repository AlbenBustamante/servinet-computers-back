package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.CloseUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailStatus;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import com.servinetcomputers.api.domain.reports.application.usecase.GetCashRegisterReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CloseService implements CloseUseCase {
    private final CashRegisterDetailRepository repository;
    private final DateTimeService dateTimeService;
    private final GetCashRegisterReportsUseCase getCashRegisterReportsUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public CashRegisterDetailReportsDto call(Integer id, BaseDto baseDto) {
        final var cashRegisterDetail = repository.get(id)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento"));

        cashRegisterDetail.getCashRegister().setStatus(CashRegisterStatus.AVAILABLE);
        cashRegisterDetail.setStatus(CashRegisterDetailStatus.CLOSED);
        cashRegisterDetail.setFinalWorking(dateTimeService.timeNow());
        cashRegisterDetail.setDetailFinalBase(baseDto);

        final var closedCashRegisterDetail = repository.save(cashRegisterDetail);

        return getCashRegisterReportsUseCase.call(closedCashRegisterDetail);
    }
}
