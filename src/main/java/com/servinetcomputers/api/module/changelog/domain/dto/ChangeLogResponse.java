package com.servinetcomputers.api.module.changelog.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import com.servinetcomputers.api.module.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChangeLogResponse extends ModelResponse {
    private final String previousData, newData;
    private final ChangeLogAction action;
    private final ChangeLogType type;
    private final CashRegisterDetailStatus currentStatus;
    private final int cashRegisterDetailId;

    public ChangeLogResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                             String previousData, String newData, ChangeLogAction action, ChangeLogType type, CashRegisterDetailStatus currentStatus, int cashRegisterDetailId) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.previousData = previousData;
        this.newData = newData;
        this.action = action;
        this.type = type;
        this.currentStatus = currentStatus;
        this.cashRegisterDetailId = cashRegisterDetailId;
    }
}