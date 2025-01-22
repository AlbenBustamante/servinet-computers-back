package com.servinetcomputers.api.domain.cashregister.application.usecase.detail;

import com.servinetcomputers.api.domain.UseCase;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailResponse;

import java.util.List;

public interface GetTransactionsUseCase extends UseCase<List<TransactionDetailResponse>, Integer> {
}
