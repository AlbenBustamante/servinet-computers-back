package com.servinetcomputers.api.core.audit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Abstract class for the dto responses.
 */
@Getter
@Setter
@ToString
public abstract class AuditableDto<ID> {
    private ID id;
    private Boolean enabled;
    private String createdBy, modifiedBy;
    private LocalDateTime createdDate, modifiedDate;
}
