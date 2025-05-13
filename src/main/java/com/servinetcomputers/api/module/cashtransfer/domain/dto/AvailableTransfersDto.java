package com.servinetcomputers.api.module.cashtransfer.domain.dto;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;

import java.util.List;

public record AvailableTransfersDto(
        List<CashRegisterDetailDto> cashRegisters,
        List<SafeDetailDto> safes
) {
}
