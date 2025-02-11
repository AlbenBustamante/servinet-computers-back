package com.servinetcomputers.api.module.cashtransfer.domain.dto;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailResponse;
import lombok.Data;

@Data
public class CreateCashTransferDto {
    private final Integer value;
    private final Integer fromCashRegisterId;
    private final Integer fromSafeId;
    private final Integer toCashRegisterId;
    private final Integer toSafeId;
    private CashRegisterDetailResponse fromCashRegister;
    private CashRegisterDetailResponse toCashRegister;
    private SafeDetailResponse fromSafe;
    private SafeDetailResponse toSafe;
}
