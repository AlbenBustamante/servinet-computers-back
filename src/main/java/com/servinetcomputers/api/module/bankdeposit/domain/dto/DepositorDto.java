package com.servinetcomputers.api.module.bankdeposit.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DepositorDto {
    private Integer id, value;
    private String fullName;
    private LocalDateTime date;
}
