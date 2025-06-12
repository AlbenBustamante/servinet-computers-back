package com.servinetcomputers.api.module.transaction.domain.dto;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTransactionDetailDto {
    private int cashRegisterDetailId, value, commission;
    private String description;
    private TransactionDetailType type;
    private LocalDateTime date;
    private TransactionDto transaction;
    private CashRegisterDetailDto cashRegisterDetail;
}
