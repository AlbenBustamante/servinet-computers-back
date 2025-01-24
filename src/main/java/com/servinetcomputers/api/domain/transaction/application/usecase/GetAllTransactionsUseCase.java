package com.servinetcomputers.api.domain.transaction.application.usecase;

import com.servinetcomputers.api.domain.UseCaseWithoutParam;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionResponse;

import java.util.List;

public interface GetAllTransactionsUseCase extends UseCaseWithoutParam<List<TransactionResponse>> {
}
