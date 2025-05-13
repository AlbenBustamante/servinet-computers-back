package com.servinetcomputers.api.module.transaction.domain.repository;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.transaction.domain.dto.CreateTransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionDetailRepository {
    TransactionDetailDto save(CreateTransactionDetailDto request);

    TransactionDetailDto save(TransactionDetailDto response);

    Optional<TransactionDetailDto> get(int transactionDetailId);

    Optional<TransactionDetailDto> getDeleted(int transactionDetailId);

    List<TransactionDetailDto> getAllByCashRegisterDetailId(int cashRegisterDetailId);

    PageResponse<TransactionDetailDto> getAllByCashRegisterDetailId(int cashRegisterDetailId, Pageable pageable);

    List<TransactionDetailDto> getAllByCodeBetween(String code, LocalDateTime startDate, LocalDateTime endDate);

    Integer sumValuesByCashRegisterDetailIdAndType(int cashRegisterDetailId, TransactionDetailType type);

    int countByCashRegisterDetailId(int cashRegisterDetailId);

    int sumCommissionBetween(LocalDateTime startDate, LocalDateTime endDate);

    int sumCommissionByCashRegisterDetailId(int cashRegisterDetailId);

    int countBetween(LocalDateTime startDate, LocalDateTime endDate);
}
