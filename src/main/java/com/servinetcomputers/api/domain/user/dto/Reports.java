package com.servinetcomputers.api.domain.user.dto;

import com.servinetcomputers.api.domain.expense.Expense;
import com.servinetcomputers.api.domain.platform.entity.PlatformTransfer;

import java.util.List;

public record Reports(
        List<PlatformTransfer> platformTransfers,
        List<Expense> expenses,
        List<Expense> discounts
) {
}
