package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetAdmCashRegisterDetailsUseCase;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter1;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.AdmCashRegistersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class GetAdmCashRegisterDetailsService implements GetAdmCashRegisterDetailsUseCase {
    private final CashRegisterDetailPersistenceAdapter1 repository;
    private final DateTimeService dateTimeService;

    /**
     * Get all the current cash register details of the day.
     *
     * <p>But, if a cash register is actually occupied, it will be loaded.</p>
     */
    @Transactional(readOnly = true)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public AdmCashRegistersDto call() {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final var currentCashRegisters = repository.getAllBetween(startDate, endDate);
        final var pendingCashRegisters = repository.getAllByStatusNotAndBefore(CashRegisterDetailStatus.CLOSED, startDate);

        final List<Integer> combinedIds = new ArrayList<>();

        currentCashRegisters.forEach(cashRegister -> combinedIds.add(cashRegister.getCashRegister().getId()));
        pendingCashRegisters.forEach(cashRegister -> combinedIds.add(cashRegister.getCashRegister().getId()));

        if (combinedIds.isEmpty()) {
            combinedIds.add(0);
        }

        final var remainingCashRegisters = repository.getLatestWhereCashRegisterIdIsNotIn(combinedIds);

        return new AdmCashRegistersDto(currentCashRegisters, pendingCashRegisters, remainingCashRegisters);
    }
}
