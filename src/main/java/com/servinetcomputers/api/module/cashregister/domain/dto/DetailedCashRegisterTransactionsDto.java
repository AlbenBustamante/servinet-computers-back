package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;

import java.util.List;

public record DetailedCashRegisterTransactionsDto(
        List<TransactionDetailDto> transactions,
        List<ExpenseDto> expenses,
        List<ExpenseDto> discounts,
        List<CashTransferDto> transfers
) {
}
