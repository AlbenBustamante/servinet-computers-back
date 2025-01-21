package com.servinetcomputers.api.domain.reports.dto;

import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferResponse;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailResponse;
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
