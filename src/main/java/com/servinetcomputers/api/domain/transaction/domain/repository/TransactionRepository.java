package com.servinetcomputers.api.domain.transaction.domain.repository;

import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionResponse;

import java.util.List;

public interface TransactionRepository {
    List<TransactionResponse> getAll();
}
