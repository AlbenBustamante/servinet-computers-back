package com.servinetcomputers.api.domain.cashregister.domain.dto;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@RequiredArgsConstructor
@Getter
@Setter
public class CreateCashRegisterDetailDto {
    private final int cashRegisterId;
    private final int userId;
    private final LocalTime initialWorking;
    private final BaseDto initialBase;
    private final BaseDto finalBase;
    private final String baseObservation;
    private CashRegisterResponse cashRegister;
    private UserResponse user;
}
