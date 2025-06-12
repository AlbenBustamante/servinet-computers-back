package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.security.service.UserLoggedService;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.CashRegisterDetailAlreadyExistsUseCase;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter1;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterRepository;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.AlreadyExistsCashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDto;
import com.servinetcomputers.api.module.user.application.usecase.GetUserCashRegisterReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.CASHIER_AUTHORITY;
import static com.servinetcomputers.api.core.util.constants.SecurityConstants.SUPERVISOR_AUTHORITY;

@RequiredArgsConstructor
@Service
public class CashRegisterDetailAlreadyExistsService implements CashRegisterDetailAlreadyExistsUseCase {
    private final CashRegisterDetailPersistenceAdapter1 repository;
    private final CashRegisterRepository cashRegisterRepository;
    private final UserLoggedService userLoggedService;
    private final DateTimeService dateTimeService;
    private final GetUserCashRegisterReportsUseCase getUserCashRegisterReportsUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = {CASHIER_AUTHORITY, SUPERVISOR_AUTHORITY})
    @Override
    public AlreadyExistsCashRegisterDetailDto call() {
        final var userId = userLoggedService.id();
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final var details = repository.getAllByUserIdWhereStatusIsNotBetween(userId, startDate, endDate, CashRegisterDetailStatus.CLOSED);
        final var alreadyExists = !details.isEmpty();

        final var myCashRegisters = alreadyExists ? getUserCashRegisterReportsUseCase.call(userId) : null;
        final List<CashRegisterDto> cashRegisters = !alreadyExists ? cashRegisterRepository.getAll() : List.of();

        return new AlreadyExistsCashRegisterDetailDto(alreadyExists, myCashRegisters, cashRegisters);
    }
}
