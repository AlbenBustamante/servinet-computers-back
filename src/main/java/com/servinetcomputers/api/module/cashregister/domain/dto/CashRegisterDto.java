package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CashRegisterDto extends AuditableDto<Integer> {
    private int numeral;
    private String description;
    private CashRegisterStatus status;
}
