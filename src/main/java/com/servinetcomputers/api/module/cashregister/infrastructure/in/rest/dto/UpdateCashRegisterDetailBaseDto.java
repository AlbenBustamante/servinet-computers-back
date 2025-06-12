package com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto;

import com.servinetcomputers.api.module.base.Base;

public record UpdateCashRegisterDetailBaseDto(
        Base base,
        boolean initial
) {
}
