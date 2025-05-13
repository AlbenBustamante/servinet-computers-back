package com.servinetcomputers.api.module.transaction.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.InvalidTempCodeException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.exception.RequiredTempCodeException;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import com.servinetcomputers.api.module.changelog.application.usecase.CreateChangeLogUseCase;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import com.servinetcomputers.api.module.transaction.application.usecase.UpdateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.CreateTransactionDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDto;
import com.servinetcomputers.api.module.transaction.domain.dto.UpdateTransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateTransactionDetailService implements UpdateTransactionDetailUseCase {
    private final TransactionDetailRepository repository;
    private final TransactionRepository transactionRepository;
    private final TempCodeRepository tempCodeRepository;
    private final CreateChangeLogUseCase createChangeLogUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public TransactionDetailDto call(Integer transactionDetailId, UpdateTransactionDetailDto dto) {
        if (dto.tempCode() == null) {
            throw new RequiredTempCodeException();
        }

        final var lastCode = tempCodeRepository.getLast();

        if (lastCode.isEmpty() || !lastCode.get().getCode().equals(dto.tempCode())) {
            throw new InvalidTempCodeException();
        }

        final var transactionDetail = repository.get(transactionDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la transacción: " + transactionDetailId));

        final var previousData = transactionDetail.copy();

        if (dto.description() != null) {
            final var transaction = getTransaction(dto.description());
            transactionDetail.setTransaction(transaction);
        }

        transactionDetail.setValue(dto.value() != null ? dto.value() : transactionDetail.getValue());
        transactionDetail.setType(dto.type() != null ? dto.type() : transactionDetail.getType());
        transactionDetail.setCommission(dto.commission() != null ? dto.commission() : transactionDetail.getCommission());
        transactionDetail.setDate(dto.date() != null ? dto.date() : transactionDetail.getDate());

        repository.save(transactionDetail);

        final var transactionDetailUpdated = repository.get(transactionDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la transacción: " + transactionDetailId));

        createChangeLog(previousData, transactionDetailUpdated);

        final var user = transactionDetailUpdated.getCashRegisterDetail().getUser();
        lastCode.get().setUsedBy(user);
        tempCodeRepository.save(lastCode.get());

        return transactionDetailUpdated;
    }

    private void createChangeLog(TransactionDetailDto previousData, TransactionDetailDto newData) {
        final var dto = new CreateChangeLogDto(
                ChangeLogAction.UPDATE,
                ChangeLogType.TRANSACTION_DETAIL,
                previousData.getCashRegisterDetailId(),
                previousData.getCashRegisterDetail().getStatus(),
                previousData,
                newData
        );

        createChangeLogUseCase.call(dto);
    }

    private TransactionDto getTransaction(String description) {
        final var transaction = transactionRepository.getByDescription(description.toUpperCase());

        if (transaction.isPresent()) {
            return transaction.get();
        }

        final var request = new CreateTransactionDto(description, null);
        return transactionRepository.save(request);
    }
}
