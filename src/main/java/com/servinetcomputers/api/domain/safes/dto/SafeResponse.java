package com.servinetcomputers.api.domain.safes.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.base.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SafeResponse extends ModelResponse {
    private final int numeral, initialBase, finalBase;
    private final BaseDto detailInitialBase, detailFinalBase;

    public SafeResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                        int numeral, BaseDto detailInitialBase, BaseDto detailFinalBase) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.numeral = numeral;
        this.detailInitialBase = detailInitialBase;
        this.detailFinalBase = detailFinalBase;
        this.initialBase = this.detailInitialBase.calculateSafeBase();
        this.finalBase = this.detailFinalBase.calculateSafeBase();
    }
}
