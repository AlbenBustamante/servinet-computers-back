package com.servinetcomputers.api.module.cashtransfer.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashTransfersByIdUseCase;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.CreateCashTransferUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import com.servinetcomputers.api.module.safes.application.usecase.detail.UpdateSafeDetailBaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateCashTransferService implements CreateCashTransferUseCase {
    private final CashTransferRepository repository;
    private final UpdateSafeDetailBaseUseCase updateSafeDetailBaseUseCase;
    private final GetCashTransfersByIdUseCase getCashTransfersByIdUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PageResponse<CashTransferDto> call(CreateCashTransferDto dto, Pageable pageable) {
        if (dto.receiverId().equals(dto.senderId()) && dto.receiverType().equals(dto.senderType())) {
            throw new BadRequestException("No puedes transferir a la misma caja");
        }

        if (dto.safeBase() != null || dto.safeDetailId() != null) {
            updateSafeDetailBaseUseCase.call(dto.safeDetailId(), dto.safeBase());
        }

        repository.save(dto);

        return getCashTransfersByIdUseCase.call(dto.currentCashRegisterDetailId(), pageable);
    }
}
