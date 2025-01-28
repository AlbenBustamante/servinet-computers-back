package com.servinetcomputers.api.module.transaction.domain.dto;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class TransactionDetailRequest {
    private final int cashRegisterDetailId;
    private final String description;
    private final TransactionDetailType type;
    private final int value;
    private final int commission;
    private final LocalDateTime date;
    private TransactionResponse transaction;
    private CashRegisterDetailResponse cashRegisterDetail;
}
