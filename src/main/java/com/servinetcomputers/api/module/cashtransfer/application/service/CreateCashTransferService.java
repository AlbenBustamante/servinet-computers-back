package com.servinetcomputers.api.module.cashtransfer.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.CreateCashTransferUseCase;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.GetCashTransferDetailsUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import com.servinetcomputers.api.module.safes.application.usecase.detail.UpdateSafeDetailBaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateCashTransferService implements CreateCashTransferUseCase {
    private final CashTransferRepository repository;
    private final GetCashTransferDetailsUseCase getDetailsUseCase;
    private final UpdateSafeDetailBaseUseCase updateSafeDetailBaseUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public CashTransferDto call(CreateCashTransferDto dto) {
        if (dto.receiverId().equals(dto.senderId()) && dto.receiverType().equals(dto.senderType())) {
            throw new BadRequestException("No puedes transferir a la misma caja");
        }

        if (dto.safeBase() != null || dto.safeDetailId() != null) {
            updateSafeDetailBaseUseCase.call(dto.safeDetailId(), dto.safeBase());
        }

        final var newCashTransfer = repository.save(dto);
        return getDetailsUseCase.call(newCashTransfer, dto.currentCashRegisterDetailId());
    }
}
