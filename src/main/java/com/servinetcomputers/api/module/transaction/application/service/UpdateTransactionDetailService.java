package com.servinetcomputers.api.module.transaction.application.service;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.transaction.application.usecase.UpdateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.UpdateTransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateTransactionDetailService implements UpdateTransactionDetailUseCase {
    private final TransactionDetailRepository repository;

    @Override
    public TransactionDetailResponse call(Integer transactionDetailId, UpdateTransactionDetailDto dto) {
        final var transactionDetail = repository.get(transactionDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la transacción: " + transactionDetailId));

        transactionDetail.setDescription(dto.description() != null ? dto.description() : transactionDetail.getDescription());
        transactionDetail.setValue(dto.value() != null ? dto.value() : transactionDetail.getValue());
        transactionDetail.setType(dto.type() != null ? dto.type() : transactionDetail.getType());
        transactionDetail.setCommission(dto.commission() != null ? dto.commission() : transactionDetail.getCommission());
        transactionDetail.setDate(dto.date() != null ? dto.date() : transactionDetail.getDate());

        return repository.save(transactionDetail);
    }
}
