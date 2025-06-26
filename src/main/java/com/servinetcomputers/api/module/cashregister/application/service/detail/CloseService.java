package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.CloseUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CloseCashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterRepository;
import com.servinetcomputers.api.module.reports.application.usecase.GetCashRegisterDetailReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CloseService implements CloseUseCase {
    private final CashRegisterDetailRepository repository;
    private final CashRegisterRepository cashRegisterRepository;
    private final DateTimeService dateTimeService;
    private final GetCashRegisterDetailReportsUseCase getCashRegisterDetailReportsUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public CashRegisterDetailReportsDto call(Integer id, CloseCashRegisterDetailDto dto) {
        final var cashRegisterDetail = repository.get(id)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento"));

        final var cashRegister = cashRegisterDetail.getCashRegister();
        cashRegister.setStatus(CashRegisterStatus.AVAILABLE);

        final var updatedCashRegister = cashRegisterRepository.save(cashRegister);

        cashRegisterDetail.setCashRegister(updatedCashRegister);
        cashRegisterDetail.setStatus(CashRegisterDetailStatus.CLOSED);
        cashRegisterDetail.setFinalWorking(dto.time() == null ? dateTimeService.now() : dateTimeService.setCurrentDayToTime(dto.time()));
        cashRegisterDetail.setDetailFinalBase(dto.base());

        final var closedCashRegisterDetail = repository.save(cashRegisterDetail);

        return getCashRegisterDetailReportsUseCase.call(closedCashRegisterDetail);
    }
}
