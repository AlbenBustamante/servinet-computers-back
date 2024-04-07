package com.servinetcomputers.api.domain.cashregister.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CashRegisterDetailResponse extends ModelResponse {
    private final int cashRegisterId;
    private final String initialWorking, initialBreak, finalBreak, finalWorking;

    public CashRegisterDetailResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                                      int cashRegisterId, String workingHours) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.cashRegisterId = cashRegisterId;

        final var hours = workingHours.split(";");

        this.initialWorking = hours[0];
        this.initialBreak = hours.length > 1 ? hours[1] : "";
        this.finalBreak = hours.length > 2 ? hours[2] : "";
        this.finalWorking = hours.length > 3 ? hours[3] : "";
    }
}
