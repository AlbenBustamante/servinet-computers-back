package com.servinetcomputers.api.domain.cashregister.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.base.BaseDto;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class CashRegisterDetailResponse extends ModelResponse {
    private final int userId;
    private final String baseObservation;
    private final LocalDateTime initialWorking, initialBreak, finalBreak, finalWorking;
    private final BaseDto initialBase, finalBase;
    private final CashRegisterResponse cashRegister;

    public CashRegisterDetailResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                                      int userId, LocalTime[] workingHours, String baseObservation, BaseDto initialBase, BaseDto finalBase, CashRegisterResponse cashRegister) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.userId = userId;

        this.initialWorking = LocalDateTime.of(LocalDate.now(), workingHours[0]);
        this.initialBreak = workingHours[1] != null ? LocalDateTime.of(LocalDate.now(), workingHours[1]) : null;
        this.finalBreak = workingHours[2] != null ? LocalDateTime.of(LocalDate.now(), workingHours[2]) : null;
        this.finalWorking = workingHours[3] != null ? LocalDateTime.of(LocalDate.now(), workingHours[3]) : null;

        this.baseObservation = baseObservation;
        this.initialBase = initialBase;
        this.finalBase = finalBase;
        this.cashRegister = cashRegister;
    }
}
