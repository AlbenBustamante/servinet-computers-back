package com.servinetcomputers.api.module.bankdeposit.domain.dto;

import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBankDepositDto {
    private Integer cashRegisterDetailId, expenseValue;
    private String collector, expenseNote;
    private BankDepositStatus status;
    private ExpenseDto expenseDto;
    private CashRegisterDetailDto cashRegisterDetailDto;
}
