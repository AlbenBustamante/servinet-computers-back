package com.servinetcomputers.api.domain.cashregister.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.base.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CashRegisterDetailResponse extends ModelResponse {
    private final int cashRegisterId;
    private final String initialWorking, initialBreak, finalBreak, finalWorking, baseObservation;
    private final BaseDto initialBase, finalBase;

    public CashRegisterDetailResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                                      int cashRegisterId, String workingHours, String baseObservation, BaseDto initialBase, BaseDto finalBase) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.cashRegisterId = cashRegisterId;

        final var hours = workingHours.split(";");

        this.initialWorking = hours[0];
        this.initialBreak = hours.length > 1 ? hours[1] : "";
        this.finalBreak = hours.length > 2 ? hours[2] : "";
        this.finalWorking = hours.length > 3 ? hours[3] : "";

        this.baseObservation = baseObservation;
        this.initialBase = initialBase;
        this.finalBase = finalBase;
    }
}
