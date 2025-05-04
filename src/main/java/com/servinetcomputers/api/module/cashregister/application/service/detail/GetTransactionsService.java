package com.servinetcomputers.api.module.cashregister.application.service.detail;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetTransactionsUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetTransactionsService implements GetTransactionsUseCase {
    private final TransactionDetailRepository repository;

    @Transactional(readOnly = true)
    @Override
    public PageResponse<TransactionDetailDto> call(Integer cashRegisterDetailId, Pageable pageable) {
        return repository.getAllByCashRegisterDetailId(cashRegisterDetailId, pageable);
    }
}
