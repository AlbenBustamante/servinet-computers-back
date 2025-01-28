package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.ModelResponse;
import com.servinetcomputers.api.module.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
public class CashRegisterDetailResponse extends ModelResponse {
    private final int userId, initialBase, finalBase;
    private final String baseObservation;
    private final BaseDto detailInitialBase;
    private final CashRegisterResponse cashRegister;
    private BaseDto detailFinalBase;
    private LocalTime initialWorking, initialBreak, finalBreak, finalWorking;
    private CashRegisterDetailStatus status;

    public CashRegisterDetailResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                                      int userId, String baseObservation, BaseDto detailInitialBase, BaseDto detailFinalBase,
                                      CashRegisterDetailStatus status, CashRegisterResponse cashRegister) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.userId = userId;
        this.baseObservation = baseObservation;
        this.detailInitialBase = detailInitialBase;
        this.detailFinalBase = detailFinalBase;
        this.cashRegister = cashRegister;

        this.initialBase = this.detailInitialBase.calculate();
        this.finalBase = this.detailFinalBase != null ? this.detailFinalBase.calculate() : 0;

        this.status = status;
    }
}
