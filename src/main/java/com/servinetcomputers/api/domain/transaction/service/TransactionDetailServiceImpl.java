package com.servinetcomputers.api.domain.transaction.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.transaction.abs.*;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailResponse;
import com.servinetcomputers.api.domain.transaction.dto.TransactionRequest;
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

    @Transactional(rollbackFor = AppException.class)
    @Override
    public TransactionDetailResponse create(TransactionDetailRequest request) {
        final var transactionId = request.transactionId();
        final var entity = mapper.toEntity(request);

        if (transactionId != null) {
            final var transaction = transactionRepository.findByIdAndEnabledTrue(transactionId)
                    .orElseThrow(() -> new NotFoundException("Transacción no encontrada: #" + transactionId));

            entity.setTransaction(transaction);
        } else {
            final var newTransaction = new TransactionRequest(request.description(), null);
            final var newEntity = transactionMapper.toEntity(newTransaction);

            entity.setTransaction(newEntity);
        }

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
