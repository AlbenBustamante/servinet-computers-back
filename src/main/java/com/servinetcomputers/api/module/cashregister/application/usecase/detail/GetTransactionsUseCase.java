package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;

import java.util.List;

public interface GetTransactionsUseCase extends UseCase<List<TransactionDetailResponse>, Integer> {
}
