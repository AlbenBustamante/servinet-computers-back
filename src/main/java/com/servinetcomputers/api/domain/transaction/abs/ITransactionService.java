package com.servinetcomputers.api.domain.transaction.abs;

import com.servinetcomputers.api.domain.transaction.dto.TransactionResponse;

import java.util.List;

public interface ITransactionService {
    List<TransactionResponse> getAll();
}
