package com.servinetcomputers.api.domain.transaction.abs;

import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailResponse;

import java.util.List;

public interface ITransactionDetailService {
    TransactionDetailResponse create(TransactionDetailRequest request);

    List<TransactionDetailResponse> getByCashRegisterDetailId(int cashRegisterDetailId);
}
