package com.servinetcomputers.api.module.bankdeposit.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DepositorDto {
    private Integer id, value;
    private String fullName;
    private LocalDateTime date;
}
