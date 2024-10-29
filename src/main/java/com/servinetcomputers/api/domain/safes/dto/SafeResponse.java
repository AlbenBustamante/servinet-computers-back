package com.servinetcomputers.api.domain.safes.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SafeResponse extends ModelResponse {
    private final int numeral;

    public SafeResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                        int numeral) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.numeral = numeral;
    }
}
