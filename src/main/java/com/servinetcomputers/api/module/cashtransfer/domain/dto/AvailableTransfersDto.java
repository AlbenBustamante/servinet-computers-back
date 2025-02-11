package com.servinetcomputers.api.module.cashtransfer.domain.dto;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailResponse;

import java.util.List;

public record AvailableTransfersDto(
        List<CashRegisterDetailResponse> cashRegisters,
        List<SafeDetailResponse> safes
) {
}
