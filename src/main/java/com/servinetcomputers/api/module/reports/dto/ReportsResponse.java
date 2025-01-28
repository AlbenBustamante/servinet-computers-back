package com.servinetcomputers.api.module.reports.dto;

import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ReportsResponse(
        List<PlatformTransferResponse> platformTransfers,
        List<ExpenseResponse> expenses,
        List<ExpenseResponse> discounts,
        List<TransactionDetailResponse> transactions
) {
}
