package com.servinetcomputers.api.module.bankdeposit.domain.dto;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDepositorDto {
    private PK pk;
    private Integer value;
    private CashRegisterDetailDto cashRegisterDetail;
    private BankDepositDto bankDeposit;

    public record PK(Integer bankDepositId, Integer cashRegisterDetailId) {
    }
}
