package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.GetTransactionsUseCase;
import com.servinetcomputers.api.domain.transaction.abs.ITransactionDetailService;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTransactionsService implements GetTransactionsUseCase {
    private final ITransactionDetailService repository;

    @Transactional(readOnly = true)
    @Override
    public List<TransactionDetailResponse> call(Integer param) {
        return repository.getByCashRegisterDetailId(param);
    }
}
