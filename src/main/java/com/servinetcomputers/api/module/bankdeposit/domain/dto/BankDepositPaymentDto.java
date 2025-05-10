package com.servinetcomputers.api.module.bankdeposit.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankDepositPaymentDto extends AuditableDto<Integer> {
    private Integer bankDepositId, value;
    private PlatformDto platform;
}
