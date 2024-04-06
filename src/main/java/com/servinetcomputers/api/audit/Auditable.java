package com.servinetcomputers.api.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

import static com.servinetcomputers.api.domain.user.util.UserConstants.CODE_LENGTH;

@MappedSuperclass
@Getter
@Setter
public abstract class Auditable {

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    @Column(length = CODE_LENGTH)
    @CreatedBy
    private String createdBy;

    @Column(length = CODE_LENGTH)
    @LastModifiedBy
    private String modifiedBy;

}
