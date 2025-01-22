package com.servinetcomputers.api.domain.transaction.domain.repository;

import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailResponse;

import java.util.List;

public interface TransactionDetailRepository {
    TransactionDetailResponse create(TransactionDetailRequest request);

    List<TransactionDetailResponse> getByCashRegisterDetailId(int cashRegisterDetailId);
}
