package com.servinetcomputers.api.module.cashtransfer.domain.repository;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CashTransferRepository {
    CashTransferDto save(CreateCashTransferDto createCashTransferDto);

    CashTransferDto save(CashTransferDto dto);

    Optional<CashTransferDto> get(int cashTransferId);

    PageResponse<CashTransferDto> getAllByCashBoxIdAndType(int id, CashBoxType type, Pageable pageable);

    List<CashTransferDto> getAllByCashBoxIdAndType(int id, CashBoxType type);

    int sumAllBySenderIdAndType(int senderId, CashBoxType senderType);

    int sumAllByReceiverIdAndType(int receiverId, CashBoxType receiverType);
}
