package com.servinetcomputers.api.core.audit;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Abstract class for the dto responses.
 */
@Getter
@Setter
public abstract class AuditableDto<ID> {
    private ID id;
    private Boolean enabled;
    private String createdBy, modifiedBy;
    private LocalDateTime createdDate, modifiedDate;
}
