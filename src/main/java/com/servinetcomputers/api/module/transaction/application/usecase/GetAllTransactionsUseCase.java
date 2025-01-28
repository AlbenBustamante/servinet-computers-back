package com.servinetcomputers.api.module.transaction.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCaseWithoutParam;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionResponse;

import java.util.List;

public interface GetAllTransactionsUseCase extends UseCaseWithoutParam<List<TransactionResponse>> {
}
