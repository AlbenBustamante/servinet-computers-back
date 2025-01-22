package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.GetTransactionsUseCase;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.domain.transaction.domain.repository.TransactionDetailRepository;
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
    public List<TransactionDetailResponse> call(Integer param) {
        return repository.getByCashRegisterDetailId(param);
    }
}
