package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.module.ModelResponse;
import com.servinetcomputers.api.module.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SafeDetailResponse extends ModelResponse {
    private BaseDto detailInitialBase, detailFinalBase;
    private final int safeId, calculatedBase;
    private final Integer initialBase, finalBase;
    private final SafeResponse safe;

    public SafeDetailResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                              BaseDto detailInitialBase, BaseDto detailFinalBase, int safeId, int calculatedBase, SafeResponse safe) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.detailInitialBase = detailInitialBase;
        this.detailFinalBase = detailFinalBase;
        this.initialBase = this.detailInitialBase != null ? this.detailInitialBase.calculate() : null;
        this.finalBase = this.detailFinalBase != null ? this.detailFinalBase.calculate() : null;
        this.calculatedBase = calculatedBase;
        this.safeId = safeId;
        this.safe = safe;
    }
}
