package com.servinetcomputers.api.domain.transaction.application.usecase;

import com.servinetcomputers.api.domain.UseCase;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailResponse;

public interface CreateTransactionDetailUseCase extends UseCase<TransactionDetailResponse, TransactionDetailRequest> {
}
