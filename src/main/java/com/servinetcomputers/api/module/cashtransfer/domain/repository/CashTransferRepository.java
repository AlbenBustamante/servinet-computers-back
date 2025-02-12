package com.servinetcomputers.api.module.cashtransfer.domain.repository;

import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CashTransferRepository {
    CashTransferDto save(CreateCashTransferDto createCashTransferDto);

    List<CashTransferDto> getAllBySenderIdOrReceiverIdBetween(int senderId, int receiverId, LocalDateTime startDate, LocalDateTime endDate);
}
