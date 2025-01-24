package com.servinetcomputers.api.domain.transaction.domain.dto;

import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.transaction.util.TransactionDetailType;
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
