package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class CreateCashRegisterDetailDto {
    private int cashRegisterId, userId;
    private LocalTime initialWorking;
    private BaseDto initialBase, finalBase;
    private String baseObservation;
    private CashRegisterDto cashRegister;
    private UserDto user;
}
