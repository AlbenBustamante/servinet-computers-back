package com.servinetcomputers.api.domain.reports.dto;

import com.servinetcomputers.api.domain.expense.persistence.entity.Expense;
import com.servinetcomputers.api.domain.platform.persistence.entity.PlatformTransfer;
import com.servinetcomputers.api.domain.transaction.persistence.entity.TransactionDetail;

import java.util.List;

public record Reports(
        List<PlatformTransfer> platformTransfers,
        List<Expense> expenses,
        List<Expense> discounts,
        List<TransactionDetail> transactions
) {
}
