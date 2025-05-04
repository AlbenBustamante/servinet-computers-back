package com.servinetcomputers.api.module.transaction.domain.repository;

import com.servinetcomputers.api.module.transaction.domain.dto.CreateTransactionDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDto;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    TransactionDto save(CreateTransactionDto request);

    TransactionDto save(TransactionDto response);

    List<TransactionDto> getAll();

    Optional<TransactionDto> getByDescription(String description);
}
