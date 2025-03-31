package com.servinetcomputers.api.module.transaction.domain.repository;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionDetailRepository {
    TransactionDetailResponse save(TransactionDetailRequest request);

    TransactionDetailResponse save(TransactionDetailResponse response);

    Optional<TransactionDetailResponse> get(int transactionDetailId);

    List<TransactionDetailResponse> getAllByCashRegisterDetailId(int cashRegisterDetailId);

    List<TransactionDetailResponse> getAllByCodeBetween(String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumValuesByCashRegisterDetailIdAndType(int cashRegisterDetailId, TransactionDetailType type);

    int countByCashRegisterDetailId(int cashRegisterDetailId);

    int sumCommissionBetween(LocalDateTime startDate, LocalDateTime endDate);

    int sumCommissionByCashRegisterDetailId(int cashRegisterDetailId);

    int countBetween(LocalDateTime startDate, LocalDateTime endDate);
}
