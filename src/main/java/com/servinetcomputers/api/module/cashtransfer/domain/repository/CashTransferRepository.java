package com.servinetcomputers.api.module.cashtransfer.domain.repository;

import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;

import java.util.List;
import java.util.Optional;

public interface CashTransferRepository {
    CashTransferDto save(CreateCashTransferDto createCashTransferDto);

    CashTransferDto save(CashTransferDto dto);

    Optional<CashTransferDto> get(int cashTransferId);

    List<CashTransferDto> getAllByCashBoxIdAndType(int id, CashBoxType type);

    int sumAllBySenderIdAndType(int senderId, CashBoxType senderType);

    int sumAllByReceiverIdAndType(int receiverId, CashBoxType receiverType);
}
