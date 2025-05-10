package com.servinetcomputers.api.module.bankdeposit.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDepositorDto {
    private PK pk;
    private Integer value;

    public record PK(Integer bankDepositId, Integer cashRegisterDetailId) {
    }
}
