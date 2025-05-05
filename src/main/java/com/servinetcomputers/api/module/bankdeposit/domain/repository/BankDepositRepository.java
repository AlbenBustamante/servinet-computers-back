package com.servinetcomputers.api.module.bankdeposit.domain.repository;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BankDepositRepository {
    BankDepositDto save(CreateBankDepositDto dto);

    List<BankDepositDto> getAllBetween(LocalDateTime startDate, LocalDateTime endDate);
}
