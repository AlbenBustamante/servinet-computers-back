package com.servinetcomputers.api.domain.safes.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import com.servinetcomputers.api.domain.base.BaseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SafeBaseResponse extends ModelResponse {
    private final int safeId;
    private final BaseDto base;

    public SafeBaseResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                            int safeId, BaseDto base) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.safeId = safeId;
        this.base = base;
    }
}
