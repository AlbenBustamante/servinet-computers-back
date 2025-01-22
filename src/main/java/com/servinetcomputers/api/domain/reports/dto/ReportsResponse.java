package com.servinetcomputers.api.domain.reports.dto;

import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferResponse;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailResponse;
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
