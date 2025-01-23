package com.servinetcomputers.api.domain.transaction.persistence.repository;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterDetailRepository;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionRequest;
import com.servinetcomputers.api.domain.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.domain.transaction.persistence.JpaTransactionDetailRepository;
import com.servinetcomputers.api.domain.transaction.persistence.JpaTransactionRepository;
import com.servinetcomputers.api.domain.transaction.persistence.entity.Transaction;
import com.servinetcomputers.api.domain.transaction.persistence.mapper.TransactionDetailMapper;
import com.servinetcomputers.api.domain.transaction.persistence.mapper.TransactionMapper;
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
    private final JpaTransactionRepository jpaTransactionRepository;
    private final TransactionMapper transactionMapper;
    private final JpaCashRegisterDetailRepository jpaCashRegisterDetailRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public TransactionDetailResponse create(TransactionDetailRequest request) {
        final var transactionFound = jpaTransactionRepository.findByDescriptionAndEnabledTrue(request.description().toUpperCase());
        Transaction transaction;

        if (transactionFound.isEmpty()) {
            final var newRequest = new TransactionRequest(request.description(), null);
            transaction = transactionMapper.toEntity(newRequest);
        } else {
            transaction = transactionFound.get();
            transaction.addUse();
        }

        transaction = jpaTransactionRepository.save(transaction);

        final var detail = mapper.toEntity(request);
        detail.setTransaction(transaction);

        final var cashRegisterDetail = jpaCashRegisterDetailRepository.findByIdAndEnabledTrue(request.cashRegisterDetailId())
                .orElseThrow(() -> new NotFoundException("Caja no encontrada: #" + request.cashRegisterDetailId()));

        detail.setCashRegisterDetail(cashRegisterDetail);

        return mapper.toResponse(repository.save(detail));
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
