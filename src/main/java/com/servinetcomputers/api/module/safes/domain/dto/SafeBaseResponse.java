package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.module.ModelResponse;
import com.servinetcomputers.api.module.base.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SafeBaseResponse extends ModelResponse {
    private final int safeDetailId, base;
    private final BaseDto detailBase;

    public SafeBaseResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                            int safeDetailId, BaseDto detailBase) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.safeDetailId = safeDetailId;
        this.detailBase = detailBase;
        this.base = this.detailBase.calculateSafeBase();
    }
}
