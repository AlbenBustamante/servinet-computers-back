package com.servinetcomputers.api.module.transaction.domain.repository;

import com.servinetcomputers.api.module.transaction.domain.dto.TransactionRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionResponse;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    TransactionResponse save(TransactionRequest request);

    TransactionResponse save(TransactionResponse response);

    List<TransactionResponse> getAll();

    Optional<TransactionResponse> getByDescription(String description);
}
