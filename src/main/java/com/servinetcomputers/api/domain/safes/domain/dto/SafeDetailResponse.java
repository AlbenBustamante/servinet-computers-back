package com.servinetcomputers.api.domain.safes.domain.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.base.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SafeDetailResponse extends ModelResponse {
    private final BaseDto detailInitialBase, detailFinalBase;
    private final int safeId, initialBase, finalBase, calculatedBase;
    private final SafeResponse safe;

    public SafeDetailResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                              BaseDto detailInitialBase, BaseDto detailFinalBase, int safeId, int calculatedBase, SafeResponse safe) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.detailInitialBase = detailInitialBase;
        this.detailFinalBase = detailFinalBase;
        this.initialBase = this.detailInitialBase != null ? this.detailInitialBase.calculateSafeBase() : 0;
        this.finalBase = this.detailFinalBase != null ? this.detailFinalBase.calculateSafeBase() : 0;
        this.calculatedBase = calculatedBase;
        this.safeId = safeId;
        this.safe = safe;
    }
}
