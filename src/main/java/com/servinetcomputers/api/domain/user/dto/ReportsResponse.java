package com.servinetcomputers.api.domain.user.dto;

import com.servinetcomputers.api.domain.expense.dto.ExpenseResponse;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ReportsResponse(
        List<PlatformTransferResponse> platformTransfers,
        List<ExpenseResponse> expenses,
        List<ExpenseResponse> discounts
) {
}
