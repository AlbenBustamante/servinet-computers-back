package com.servinetcomputers.api.module.bankdeposit.domain.dto;

import com.servinetcomputers.api.core.audit.infra.AuditableDto;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BankDepositDto extends AuditableDto<Integer> {
    private String collector, openedBy;
    private Integer totalCollected;
    private BankDepositStatus status;
    private ExpenseDto expense;
    private List<DepositorDto> depositors;
    private List<BankDepositPaymentDto> payments;
}
