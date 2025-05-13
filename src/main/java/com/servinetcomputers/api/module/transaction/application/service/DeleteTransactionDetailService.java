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
import com.servinetcomputers.api.module.transaction.application.usecase.DeleteTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteTransactionDetailService implements DeleteTransactionDetailUseCase {
    private final TransactionDetailRepository transactionDetailRepository;
    private final TempCodeRepository tempCodeRepository;
    private final CreateChangeLogUseCase createChangeLogUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public void call(Integer transactionDetailId, Integer tempCode) {
        if (tempCode == null) {
            throw new RequiredTempCodeException();
        }

        final var lastTempCode = tempCodeRepository.getLast();

        if (lastTempCode.isEmpty() || !lastTempCode.get().getCode().equals(tempCode)) {
            throw new InvalidTempCodeException();
        }

        final var transactionDetail = transactionDetailRepository.get(transactionDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada #" + transactionDetailId));

        final var previousData = transactionDetail.copy();

        transactionDetail.setEnabled(false);
        transactionDetailRepository.save(transactionDetail);

        final var newData = transactionDetailRepository.getDeleted(transactionDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la transacción: " + transactionDetailId));

        createChangeLog(previousData, newData);

        final var user = transactionDetail.getCashRegisterDetail().getUser();

        lastTempCode.get().setUsedBy(user);
        tempCodeRepository.save(lastTempCode.get());
    }

    private void createChangeLog(TransactionDetailDto previousData, Object newData) {
        final var dto = new CreateChangeLogDto(
                ChangeLogAction.DELETE,
                ChangeLogType.TRANSACTION_DETAIL,
                previousData.getCashRegisterDetailId(),
                previousData.getCashRegisterDetail().getStatus(),
                previousData,
                newData
        );

        createChangeLogUseCase.call(dto);
    }
}
