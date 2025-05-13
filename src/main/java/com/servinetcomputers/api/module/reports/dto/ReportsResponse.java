package com.servinetcomputers.api.module.reports.dto;

import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import lombok.Builder;

import java.util.List;

@Builder
public record ReportsResponse(
        List<PlatformTransferDto> platformTransfers,
        List<ExpenseDto> expenses,
        List<ExpenseDto> discounts,
        List<TransactionDetailDto> transactions
) {
}
