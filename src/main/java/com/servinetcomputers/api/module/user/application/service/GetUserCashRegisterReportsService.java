package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.MyCashRegistersReports;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter;
import com.servinetcomputers.api.module.reports.application.usecase.GetCashRegisterDetailReportsUseCase;
import com.servinetcomputers.api.module.user.application.usecase.GetUserCashRegisterReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetUserCashRegisterReportsService implements GetUserCashRegisterReportsUseCase {
    private final CashRegisterDetailPersistenceAdapter repository;
    private final DateTimeService dateTimeService;
    private final GetCashRegisterDetailReportsUseCase getCashRegisterDetailReportsUseCase;

    @Transactional(readOnly = true)
    @Override
    public MyCashRegistersReports call(Integer param) {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final var details = repository.getAllByUserIdBetween(param, startDate, endDate);
        final List<CashRegisterDetailReportsDto> reports = new ArrayList<>(details.size());

        var total = CashRegisterDetailReportsDto.empty(details.get(0));

        for (final var cashRegisterDetail : details) {
            final var report = getCashRegisterDetailReportsUseCase.call(cashRegisterDetail);

            reports.add(report);
            total = total.sum(report);
        }

        return new MyCashRegistersReports(reports, total);
    }
}
