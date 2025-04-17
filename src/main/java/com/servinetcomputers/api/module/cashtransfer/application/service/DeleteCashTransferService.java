package com.servinetcomputers.api.module.cashtransfer.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.InvalidTempCodeException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.exception.RequiredTempCodeException;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.DeleteCashTransferUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import com.servinetcomputers.api.module.changelog.application.usecase.CreateChangeLogUseCase;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteCashTransferService implements DeleteCashTransferUseCase {
    private final CashTransferRepository repository;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;
    private final TempCodeRepository tempCodeRepository;
    private final CreateChangeLogUseCase createChangeLogUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public void call(Integer cashRegisterDetailId, Integer cashTransferId, Integer tempCode) {
        if (tempCode == null) {
            throw new RequiredTempCodeException();
        }

        final var lastTempCode = tempCodeRepository.getLast().orElseThrow(RequiredTempCodeException::new);

        if (!lastTempCode.getCode().equals(tempCode)) {
            throw new InvalidTempCodeException();
        }

        final var cashTransfer = repository.get(cashTransferId)
                .orElseThrow(() -> new NotFoundException("No se encontró la transferencia: " + cashTransferId));

        final var previousData = cashTransfer.copy();

        cashTransfer.setEnabled(false);
        repository.save(cashTransfer);

        final var cashRegisterDetail = cashRegisterDetailRepository.get(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada: " + cashRegisterDetailId));

        createChangeLog(previousData, cashRegisterDetail);

        lastTempCode.setUsedBy(cashRegisterDetail.getUser());
        tempCodeRepository.save(lastTempCode);
    }

    private void createChangeLog(Object previousData, CashRegisterDetailResponse cashRegisterDetail) {
        final var dto = new CreateChangeLogDto(
                ChangeLogAction.DELETE,
                ChangeLogType.CASH_TRANSFER,
                cashRegisterDetail.getId(),
                cashRegisterDetail.getStatus(),
                previousData,
                null
        );

        createChangeLogUseCase.call(dto);
    }
}
