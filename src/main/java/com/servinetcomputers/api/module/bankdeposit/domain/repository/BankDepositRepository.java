package com.servinetcomputers.api.module.bankdeposit.domain.repository;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BankDepositRepository {
    BankDepositDto save(CreateBankDepositDto dto);

    Optional<BankDepositDto> get(Integer bankDepositId);

    List<BankDepositDto> getAllBetween(LocalDateTime startDate, LocalDateTime endDate);
}
