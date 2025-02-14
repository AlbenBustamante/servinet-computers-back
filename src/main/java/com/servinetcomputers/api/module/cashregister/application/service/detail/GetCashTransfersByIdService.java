package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashTransfersByIdUseCase;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.GetCashTransferDetailsUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetCashTransfersByIdService implements GetCashTransfersByIdUseCase {
    private final CashTransferRepository cashTransferRepository;
    private final DateTimeService dateTimeService;
    private final GetCashTransferDetailsUseCase getDetailsUseCase;

    @Transactional(readOnly = true)
    @Override
    public List<CashTransferDto> call(Integer cashRegisterDetailId) {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final var cashTransfers = cashTransferRepository
                .getAllBySenderIdOrReceiverIdBetween(cashRegisterDetailId, cashRegisterDetailId, startDate, endDate);

        return cashTransfers
                .stream()
                .map(cashTransfer -> getDetailsUseCase.call(cashTransfer, cashRegisterDetailId))
                .toList();
    }
}
