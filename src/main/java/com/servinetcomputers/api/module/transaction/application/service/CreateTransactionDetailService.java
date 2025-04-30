package com.servinetcomputers.api.module.transaction.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.transaction.application.usecase.CreateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionResponse;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateTransactionDetailService implements CreateTransactionDetailUseCase {
    private final TransactionDetailRepository repository;
    private final TransactionRepository transactionRepository;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PageResponse<TransactionDetailResponse> call(TransactionDetailRequest request, Pageable pageable) {
        final var transaction = getTransaction(request.getDescription());
        request.setTransaction(transaction);

        final var cashRegisterDetail = cashRegisterDetailRepository.get(request.getCashRegisterDetailId())
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento: #" + request.getCashRegisterDetailId()));

        request.setCashRegisterDetail(cashRegisterDetail);

        repository.save(request);

        return repository.getAllByCashRegisterDetailId(cashRegisterDetail.getId(), pageable);
    }

    private TransactionResponse getTransaction(String description) {
        final var transaction = transactionRepository.getByDescription(description.toUpperCase());

        if (transaction.isPresent()) {
            transaction.get().addUse();
            return transactionRepository.save(transaction.get());
        }

        final var request = new TransactionRequest(description, null);
        return transactionRepository.save(request);
    }
}
