package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.ModelResponse;
import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.user.domain.dto.UserResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CashRegisterDetailResponse extends ModelResponse {
    private final int userId;
    private final Integer initialBase, finalBase;
    private final String baseObservation;
    private BaseDto detailInitialBase, detailFinalBase;
    private CashRegisterResponse cashRegister;
    private LocalDateTime initialWorking, initialBreak, finalBreak, finalWorking;
    private CashRegisterDetailStatus status;
    private UserResponse user;

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
        this.finalBase = this.detailFinalBase != null ? this.detailFinalBase.calculate() : null;

        this.status = status;
    }
}
