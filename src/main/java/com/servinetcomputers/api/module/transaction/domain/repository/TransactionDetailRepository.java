package com.servinetcomputers.api.module.transaction.domain.repository;

import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionDetailRepository {
    TransactionDetailResponse save(TransactionDetailRequest request);

    List<TransactionDetailResponse> getByCashRegisterDetailId(int cashRegisterDetailId, LocalDateTime startDate, LocalDateTime endDate);

    List<TransactionDetailResponse> getAllByCodeBetween(String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumDeposits(String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumWithdrawals(String code, LocalDateTime startDate, LocalDateTime endDate);
}
