package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CashRegisterDetailDto extends AuditableDto<Integer> {
    private Integer userId, initialBase, finalBase;
    private String baseObservation;
    private BaseDto detailInitialBase, detailFinalBase;
    private CashRegisterDto cashRegister;
    private LocalDateTime initialWorking, initialBreak, finalBreak, finalWorking;
    private CashRegisterDetailStatus status;
    private UserDto user;
}
