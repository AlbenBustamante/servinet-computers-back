package com.servinetcomputers.api.domain.cashregister.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.base.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CashRegisterDetailResponse extends ModelResponse {
    private final int userId;
    private final String initialWorking, initialBreak, finalBreak, finalWorking, baseObservation;
    private final BaseDto initialBase, finalBase;
    private final CashRegisterResponse cashRegister;

    public CashRegisterDetailResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                                      int userId, String[] workingHours, String baseObservation, BaseDto initialBase, BaseDto finalBase, CashRegisterResponse cashRegister) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.userId = userId;

        this.initialWorking = workingHours[0];
        this.initialBreak = workingHours[1].isBlank() ? "--:--" : workingHours[1];
        this.finalBreak = workingHours[2].isBlank() ? "--:--" : workingHours[2];
        this.finalWorking = workingHours[3].isBlank() ? "--:--" : workingHours[3];

        this.baseObservation = baseObservation;
        this.initialBase = initialBase;
        this.finalBase = finalBase;
        this.cashRegister = cashRegister;
    }
}
