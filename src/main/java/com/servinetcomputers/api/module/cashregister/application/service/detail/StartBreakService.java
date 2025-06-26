package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.StartBreakUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StartBreakService implements StartBreakUseCase {
    private final CashRegisterDetailRepository repository;
    private final DateTimeService dateTimeService;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public CashRegisterDetailDto call(Integer param) {
        final var cashRegisterDetail = repository.get(param)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento: #" + param));

        cashRegisterDetail.setInitialBreak(dateTimeService.now());
        cashRegisterDetail.setStatus(CashRegisterDetailStatus.RESTING);

        return repository.save(cashRegisterDetail);
    }
}
