package com.servinetcomputers.api.domain.cashregister.domain.dto;

import com.servinetcomputers.api.domain.base.BaseDto;

import java.time.LocalTime;

public record CashRegisterDetailRequest(
        int cashRegisterId,
        int userId,
        LocalTime initialWorking,
        BaseDto initialBase,
        BaseDto finalBase,
        String baseObservation
) {
}
