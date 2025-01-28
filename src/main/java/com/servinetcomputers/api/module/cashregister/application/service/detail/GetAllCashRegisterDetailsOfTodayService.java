package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetAllCashRegisterDetailsOfTodayUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class GetAllCashRegisterDetailsOfTodayService implements GetAllCashRegisterDetailsOfTodayUseCase {
    private final CashRegisterDetailRepository repository;
    private final DateTimeService dateTimeService;

    @Transactional(readOnly = true)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public List<CashRegisterDetailResponse> call() {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        return repository.getAllBetween(startDate, endDate);
    }
}
