package com.servinetcomputers.api.domain.cashregister.dto;

import com.servinetcomputers.api.domain.base.BaseDto;

public record CashRegisterDetailRequest(
        int cashRegisterId,
        int userId,
        String workingHours,
        BaseDto initialBase,
        BaseDto finalBase,
        String baseObservation
) {
}
