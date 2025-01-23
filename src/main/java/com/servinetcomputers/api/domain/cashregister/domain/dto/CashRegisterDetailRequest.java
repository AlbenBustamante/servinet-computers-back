package com.servinetcomputers.api.domain.cashregister.domain.dto;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import lombok.Data;

import java.time.LocalTime;

@Data
public class CashRegisterDetailRequest {
    private final int cashRegisterId;
    private final int userId;
    private final LocalTime initialWorking;
    private final BaseDto initialBase;
    private final BaseDto finalBase;
    private final String baseObservation;
    private CashRegisterResponse cashRegister;
    private UserResponse user;
}
