package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter;
import com.servinetcomputers.api.module.user.application.usecase.GetJourneysUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GetJourneysService implements GetJourneysUseCase {
    private final CashRegisterDetailPersistenceAdapter cashRegisterDetailPersistenceAdapter;

    @Override
    public List<CashRegisterDetailDto> call(Integer userId, YearMonth month) {
        final var startDate = month.atDay(1).atStartOfDay();
        final var endDate = month.plusMonths(1).atDay(1).atStartOfDay();

        return cashRegisterDetailPersistenceAdapter.getAllByUserIdBetween(userId, startDate, endDate);
    }
}
