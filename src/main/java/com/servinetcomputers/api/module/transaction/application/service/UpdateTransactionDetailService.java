package com.servinetcomputers.api.module.transaction.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.InvalidTempCodeException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import com.servinetcomputers.api.module.transaction.application.usecase.UpdateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.UpdateTransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateTransactionDetailService implements UpdateTransactionDetailUseCase {
    private final TransactionDetailRepository repository;
    private final TempCodeRepository tempCodeRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public TransactionDetailResponse call(Integer transactionDetailId, UpdateTransactionDetailDto dto) {
        if (dto.tempCode() == null) {
            throw new BadRequestException("El código es requerido");
        }

        final var lastCode = tempCodeRepository.getLast();

        if (lastCode.isEmpty() || !lastCode.get().getCode().equals(dto.tempCode())) {
            throw new InvalidTempCodeException();
        }

        final var transactionDetail = repository.get(transactionDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la transacción: " + transactionDetailId));

        transactionDetail.setDescription(dto.description() != null ? dto.description() : transactionDetail.getDescription());
        transactionDetail.setValue(dto.value() != null ? dto.value() : transactionDetail.getValue());
        transactionDetail.setType(dto.type() != null ? dto.type() : transactionDetail.getType());
        transactionDetail.setCommission(dto.commission() != null ? dto.commission() : transactionDetail.getCommission());
        transactionDetail.setDate(dto.date() != null ? dto.date() : transactionDetail.getDate());

        final var transactionDetailUpdated = repository.save(transactionDetail);
        final var user = transactionDetailUpdated.getCashRegisterDetail().getUser();
        lastCode.get().setUsedBy(user);

        tempCodeRepository.save(lastCode.get());

        return transactionDetailUpdated;
    }
}
