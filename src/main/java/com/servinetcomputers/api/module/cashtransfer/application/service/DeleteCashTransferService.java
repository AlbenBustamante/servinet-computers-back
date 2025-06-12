package com.servinetcomputers.api.module.cashtransfer.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.InvalidTempCodeException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.exception.RequiredTempCodeException;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter1;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.DeleteCashTransferUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import com.servinetcomputers.api.module.changelog.application.usecase.CreateChangeLogUseCase;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeBaseDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteCashTransferService implements DeleteCashTransferUseCase {
    private final CashTransferRepository repository;
    private final CashRegisterDetailPersistenceAdapter1 cashRegisterDetailPersistenceAdapter;
    private final SafeDetailRepository safeDetailRepository;
    private final SafeBaseRepository safeBaseRepository;
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

        final var receiverIsSafe = cashTransfer.getReceiverType() == CashBoxType.SAFE;
        final var senderIsSafe = cashTransfer.getSenderType() == CashBoxType.SAFE;

        if (receiverIsSafe || senderIsSafe) {
            var safeDetail = safeDetailRepository.get(receiverIsSafe ? cashTransfer.getReceiverId() : cashTransfer.getSenderId())
                    .orElseThrow(() -> new NotFoundException("No se encontró el movimiento de caja fuerte"));

            final var amount = cashTransfer.getSafeAmount();
            final var denomination = cashTransfer.getSafeDenomination();
            final var base = safeDetail.getDetailFinalBase();
            final var newBase = base.addOrSubtract(amount, denomination, senderIsSafe);

            safeDetail.setDetailFinalBase(newBase);
            safeDetail = safeDetailRepository.save(safeDetail);

            final var safeBase = new CreateSafeBaseDto(safeDetail.getId(), newBase, safeDetail);
            safeBaseRepository.save(safeBase);
        }

        final var cashRegisterDetail = cashRegisterDetailPersistenceAdapter.get(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada: " + cashRegisterDetailId));

        final var newData = repository.getDeleted(cashTransferId)
                .orElseThrow(() -> new NotFoundException("No se encontró la transferencia: " + cashTransferId));

        createChangeLog(previousData, cashRegisterDetail, newData);

        lastTempCode.setUsedBy(cashRegisterDetail.getUser());
        tempCodeRepository.save(lastTempCode);
    }

    private void createChangeLog(Object previousData, CashRegisterDetailDto cashRegisterDetail, Object newData) {
        final var dto = new CreateChangeLogDto(
                ChangeLogAction.DELETE,
                ChangeLogType.CASH_TRANSFER,
                cashRegisterDetail.getId(),
                cashRegisterDetail.getStatus(),
                previousData,
                newData
        );

        createChangeLogUseCase.call(dto);
    }
}
