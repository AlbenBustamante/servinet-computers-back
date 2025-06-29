package com.servinetcomputers.api.module.platform.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.platform.application.usecase.LoadPlatformBalanceByIdUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformBalanceRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LoadPlatformBalanceByIdService implements LoadPlatformBalanceByIdUseCase {
    private final PlatformRepository repository;
    private final PlatformBalanceRepository balanceRepository;
    private final DateTimeService dateTimeService;

    @Override
    public PlatformBalanceDto call(Integer platformId, LocalDate date) {
        final var balances = balanceRepository.getAllByPlatformIdBetween(platformId, date.atStartOfDay(), date.plusDays(1).atStartOfDay());

        if (!balances.isEmpty()) {
            return balances.get(0);
        }

        if (!date.isEqual(dateTimeService.dateNow())) {
            throw new NotFoundException("No se encontraron movimientos para el día seleccionado");
        }

        final var lastBalance = balanceRepository.getLastByPlatformId(platformId);
        final var newBalance = lastBalance.isPresent() ? lastBalance.get().getFinalBalance() : 0;
        final var platform = repository.get(platformId).orElseThrow(() -> new NotFoundException("No se encontró la plataforma: " + platformId));

        final var request = new CreatePlatformBalanceDto(newBalance, 0, platform);

        return balanceRepository.save(request);
    }
}
