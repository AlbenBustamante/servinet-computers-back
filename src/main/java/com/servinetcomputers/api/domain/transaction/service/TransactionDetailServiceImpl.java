package com.servinetcomputers.api.domain.transaction.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.transaction.abs.*;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailResponse;
import com.servinetcomputers.api.domain.transaction.dto.TransactionRequest;
import com.servinetcomputers.api.domain.transaction.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionDetailServiceImpl implements ITransactionDetailService {

    private final TransactionDetailRepository repository;
    private final TransactionDetailMapper mapper;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public TransactionDetailResponse create(TransactionDetailRequest request) {
        final var transactionId = request.transactionId();
        final var entity = mapper.toEntity(request);
        Transaction transaction;

        if (transactionId != null) {
            transaction = transactionRepository.findByIdAndEnabledTrue(transactionId)
                    .orElseThrow(() -> new NotFoundException("Transacción no encontrada: #" + transactionId));
            transaction.addUse();
        } else {
            final var newTransaction = new TransactionRequest(request.description(), null);
            transaction = transactionMapper.toEntity(newTransaction);
        }

        transaction = transactionRepository.save(transaction);
        entity.setTransaction(transaction);

        final var cashRegisterDetail = cashRegisterDetailRepository.findByIdAndEnabledTrue(request.cashRegisterDetailId())
                .orElseThrow(() -> new NotFoundException("Caja no encontrada: #" + request.cashRegisterDetailId()));

        entity.setCashRegisterDetail(cashRegisterDetail);

        return mapper.toResponse(repository.save(entity));
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

}
