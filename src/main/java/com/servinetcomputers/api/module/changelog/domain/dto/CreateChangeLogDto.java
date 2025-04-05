package com.servinetcomputers.api.module.changelog.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CreateChangeLogDto {
    private final String previousData, newData;
    private final ChangeLogAction action;
    private final ChangeLogType type;
    private final Integer cashRegisterDetailId;
    private final CashRegisterDetailStatus currentStatus;
    private CashRegisterDetailResponse cashRegisterDetail;
}
