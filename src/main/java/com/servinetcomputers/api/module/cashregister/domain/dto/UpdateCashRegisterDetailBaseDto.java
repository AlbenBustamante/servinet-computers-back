package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.module.base.BaseDto;

public record UpdateCashRegisterDetailBaseDto(
        BaseDto base,
        boolean initial
) {
}
