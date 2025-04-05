package com.servinetcomputers.api.module.changelog.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;

public record CreateChangeLogDto(
        String previousData,
        String newData,
        ChangeLogAction action,
        ChangeLogType type,
        CashRegisterDetailStatus currentStatus,
        CashRegisterDetailResponse cashRegisterDetail
) {
}
