package com.servinetcomputers.api.module.transaction.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.transaction.application.usecase.CreateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionResponse;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
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
    public TransactionDetailResponse call(TransactionDetailRequest param) {
        final var transaction = getTransaction(param.getDescription());
        param.setTransaction(transaction);

        final var cashRegisterDetail = cashRegisterDetailRepository.get(param.getCashRegisterDetailId())
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento: #" + param.getCashRegisterDetailId()));

        param.setCashRegisterDetail(cashRegisterDetail);

        return repository.save(param);
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
