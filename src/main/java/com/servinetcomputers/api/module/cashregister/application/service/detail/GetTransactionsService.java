package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetTransactionsUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTransactionsService implements GetTransactionsUseCase {
    private final TransactionDetailRepository repository;
    private final DateTimeService dateTimeService;

    @Transactional(readOnly = true)
    @Override
    public List<TransactionDetailResponse> call(Integer param) {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        return repository.getAllByCashRegisterDetailIdBetween(param, startDate, endDate);
    }
}
