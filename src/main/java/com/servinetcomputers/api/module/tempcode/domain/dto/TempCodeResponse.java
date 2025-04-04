package com.servinetcomputers.api.module.tempcode.domain.dto;

import com.servinetcomputers.api.module.ModelResponse;
import com.servinetcomputers.api.module.user.domain.dto.UserResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TempCodeResponse extends ModelResponse {
    private final Integer code;
    private UserResponse usedBy;

    public TempCodeResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate, String createdBy, String modifiedBy,
                            int code, UserResponse usedBy) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.code = code;
        this.usedBy = usedBy;
    }
}
