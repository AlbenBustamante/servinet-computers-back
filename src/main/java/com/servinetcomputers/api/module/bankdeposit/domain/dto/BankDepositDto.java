package com.servinetcomputers.api.module.bankdeposit.domain.dto;

import com.servinetcomputers.api.core.audit.AuditableDto;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDepositDto extends AuditableDto<Integer> {
    private String collector;
    private BankDepositStatus status;
    private ExpenseDto expense;
    private CashRegisterDetailDto cashRegisterDetail;
}
