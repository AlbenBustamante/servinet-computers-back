package com.servinetcomputers.api.module.tempcode.domain.dto;

import com.servinetcomputers.api.module.ModelResponse;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class TempCodeResponse extends ModelResponse {
    private final Integer code, usedBy;

    public TempCodeResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                            int code, int usedBy) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.code = code;
        this.usedBy = usedBy;
    }
}
