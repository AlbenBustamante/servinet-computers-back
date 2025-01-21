package com.servinetcomputers.api.domain.expense.domain.repository;

import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;

import java.util.List;

public interface ExpenseRepository {
    ExpenseResponse create(ExpenseRequest request);

    List<ExpenseResponse> getByCashRegisterDetailId(int cashRegisterDetailId);
}
