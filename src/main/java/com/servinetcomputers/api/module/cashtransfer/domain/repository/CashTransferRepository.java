package com.servinetcomputers.api.module.cashtransfer.domain.repository;

import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;

import java.util.List;

public interface CashTransferRepository {
    CashTransferDto save(CreateCashTransferDto createCashTransferDto);

    List<CashTransferDto> getAllByCashBoxIdAndType(int id, CashBoxType type);

    int sumAllBySenderIdAndType(int senderId, CashBoxType senderType);

    int sumAllByReceiverIdAndType(int receiverId, CashBoxType receiverType);
}
