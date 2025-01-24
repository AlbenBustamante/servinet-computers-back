package com.servinetcomputers.api.domain.transaction.application.service;

import com.servinetcomputers.api.domain.transaction.application.usecase.GetAllTransactionsUseCase;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionResponse;
import com.servinetcomputers.api.domain.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllTransactionsService implements GetAllTransactionsUseCase {
    private final TransactionRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<TransactionResponse> call() {
        return repository.getAll();
    }
}
