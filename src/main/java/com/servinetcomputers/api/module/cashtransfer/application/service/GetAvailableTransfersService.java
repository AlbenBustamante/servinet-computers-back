package com.servinetcomputers.api.module.cashtransfer.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.security.service.UserLoggedService;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.GetAvailableTransfersUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.AvailableTransfersDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetAvailableTransfersService implements GetAvailableTransfersUseCase {
    private final UserLoggedService userLoggedService;
    private final DateTimeService dateTimeService;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;
    private final SafeDetailRepository safeDetailRepository;

    @Transactional(readOnly = true)
    @Override
    public AvailableTransfersDto call() {
        final var userId = userLoggedService.id();

        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final var cashRegisters = cashRegisterDetailRepository.getAllWhereUserIdIsNotAndBetween(userId, startDate, endDate);
        final var safes = safeDetailRepository.getAllByDateBetween(startDate, endDate);

        return new AvailableTransfersDto(cashRegisters, safes);
    }
}
