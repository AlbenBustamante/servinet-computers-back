package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashTransfersByIdUseCase;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.GetCashTransferDetailsUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetCashTransfersByIdService implements GetCashTransfersByIdUseCase {
    private final CashTransferRepository cashTransferRepository;
    private final GetCashTransferDetailsUseCase getDetailsUseCase;

    @Transactional(readOnly = true)
    @Override
    public PageResponse<CashTransferDto> call(Integer cashRegisterDetailId, Pageable pageable) {
        final var page = cashTransferRepository
                .getAllByCashBoxIdAndType(cashRegisterDetailId, CashBoxType.CASH_REGISTER, pageable);

        final var cashTransfers = page.getContent()
                .stream()
                .map(cashTransferDto -> getDetailsUseCase.call(cashTransferDto, cashRegisterDetailId))
                .toList();

        page.setContent(cashTransfers);

        return page;
    }
}
