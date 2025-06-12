package com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto;

import com.servinetcomputers.api.module.base.Base;

import java.time.LocalTime;

public record CloseCashRegisterDetailDto(Base base, LocalTime time) {
}
