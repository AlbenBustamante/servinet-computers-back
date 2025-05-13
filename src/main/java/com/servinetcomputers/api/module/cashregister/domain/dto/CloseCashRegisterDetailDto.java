package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.module.base.BaseDto;

import java.time.LocalTime;

public record CloseCashRegisterDetailDto(BaseDto base, LocalTime time) {
}
