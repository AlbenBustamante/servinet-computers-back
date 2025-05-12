package com.servinetcomputers.api.module.bankdeposit.domain.dto;

public record CreateBankDepositPaymentDto(
        Integer bankDepositId,
        Integer platformId,
        Integer value
) {
}
