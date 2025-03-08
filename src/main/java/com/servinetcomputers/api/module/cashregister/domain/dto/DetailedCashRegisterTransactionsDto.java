package com.servinetcomputers.api.module.cashregister.domain.dto;

import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;

import java.util.List;

public record DetailedCashRegisterTransactionsDto(
        List<TransactionDetailResponse> transactions,
        List<ExpenseResponse> expenses,
        List<ExpenseResponse> discounts,
        List<CashTransferDto> transfers
) {
}
