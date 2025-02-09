package com.servinetcomputers.api.module.cashregister.application.service.detail;

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

    @Transactional(readOnly = true)
    @Override
    public List<TransactionDetailResponse> call(Integer cashRegisterDetailId) {
        return repository.getAllByCashRegisterDetailId(cashRegisterDetailId);
    }
}
