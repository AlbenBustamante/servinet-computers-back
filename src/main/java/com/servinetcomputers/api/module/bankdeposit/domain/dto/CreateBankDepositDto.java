package com.servinetcomputers.api.module.bankdeposit.domain.dto;

import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBankDepositDto {
    private String collector, expense;
    private BankDepositStatus status;
    private ExpenseDto expenseDto;
}
