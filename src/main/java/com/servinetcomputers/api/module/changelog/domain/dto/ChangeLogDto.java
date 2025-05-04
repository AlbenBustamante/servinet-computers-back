package com.servinetcomputers.api.module.changelog.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeLogDto extends AuditableDto<Integer> {
    private String previousData, newData;
    private ChangeLogAction action;
    private ChangeLogType type;
    private CashRegisterDetailStatus currentStatus;
    private Integer cashRegisterDetailId;
}
