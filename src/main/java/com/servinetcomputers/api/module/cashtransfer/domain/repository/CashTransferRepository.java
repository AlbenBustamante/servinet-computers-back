package com.servinetcomputers.api.module.cashtransfer.domain.repository;

import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;

import java.util.List;

public interface CashTransferRepository {
    CashTransferDto save(CreateCashTransferDto createCashTransferDto);

    List<CashTransferDto> getAllByUserId(int userId);
}
