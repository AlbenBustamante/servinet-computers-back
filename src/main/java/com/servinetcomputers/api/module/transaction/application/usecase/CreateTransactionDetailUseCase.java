package com.servinetcomputers.api.module.transaction.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;

public interface CreateTransactionDetailUseCase extends UseCase<TransactionDetailResponse, TransactionDetailRequest> {
}
