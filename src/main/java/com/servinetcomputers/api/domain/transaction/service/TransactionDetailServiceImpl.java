package com.servinetcomputers.api.domain.transaction.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterDetailRepository;
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

    private final JpaTransactionDetailRepository repository;
    private final TransactionDetailMapper mapper;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final JpaCashRegisterDetailRepository jpaCashRegisterDetailRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public TransactionDetailResponse create(TransactionDetailRequest request) {
        final var transactionFound = transactionRepository.findByDescriptionAndEnabledTrue(request.description().toUpperCase());
        Transaction transaction;

        if (transactionFound.isEmpty()) {
            final var newRequest = new TransactionRequest(request.description(), null);
            transaction = transactionMapper.toEntity(newRequest);
        } else {
            transaction = transactionFound.get();
            transaction.addUse();
        }

        transaction = transactionRepository.save(transaction);

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

}
