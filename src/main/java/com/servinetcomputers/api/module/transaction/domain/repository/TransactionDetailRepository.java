package com.servinetcomputers.api.module.transaction.domain.repository;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionDetailRepository {
    TransactionDetailResponse save(TransactionDetailRequest request);

    List<TransactionDetailResponse> getAllByCashRegisterDetailId(int cashRegisterDetailId);

    List<TransactionDetailResponse> getAllByCodeBetween(String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumValuesByCashRegisterDetailIdAndType(int cashRegisterDetailId, TransactionDetailType type);
}
