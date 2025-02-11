package com.servinetcomputers.api.module.cashtransfer.domain.dto;

public record CashTransferDto(
        int id,
        int value,
        boolean receive,
        String cashier
) {
}
