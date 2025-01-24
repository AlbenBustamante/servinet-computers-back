package com.servinetcomputers.api.domain.transaction.persistence.repository;

import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.domain.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.domain.transaction.persistence.JpaTransactionDetailRepository;
import com.servinetcomputers.api.domain.transaction.persistence.mapper.TransactionDetailMapper;
import com.servinetcomputers.api.domain.transaction.util.TransactionDetailType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionDetailRepositoryImpl implements TransactionDetailRepository {
    private final JpaTransactionDetailRepository repository;
    private final TransactionDetailMapper mapper;

    @Override
    public TransactionDetailResponse save(TransactionDetailRequest request) {
        final var entity = mapper.toEntity(request);
        final var newDetail = repository.save(entity);

        return mapper.toResponse(newDetail);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionDetailResponse> getByCashRegisterDetailId(int cashRegisterDetailId) {
        final var today = LocalDate.now();
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now());

        final var details = repository.findAllByCashRegisterDetailIdAndEnabledTrueAndCreatedDateBetween(cashRegisterDetailId, startDate, endDate);

        return mapper.toResponses(details);
    }

    @Override
    public Integer sumDeposits(String code, LocalDateTime startDate, LocalDateTime endDate) {
        final var deposits = repository.sumAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate, TransactionDetailType.DEPOSIT);
        return deposits != null ? deposits : 0;
    }

    @Override
    public Integer sumWithdrawals(String code, LocalDateTime startDate, LocalDateTime endDate) {
        final var withdrawals = repository.sumAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate, TransactionDetailType.WITHDRAWAL);
        return withdrawals != null ? withdrawals : 0;
    }
}
