package com.servinetcomputers.api.domain.expense.abs;

import com.servinetcomputers.api.domain.expense.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.dto.ExpenseResponse;

import java.util.List;

public interface IExpenseService {

    ExpenseResponse create(ExpenseRequest request);

    List<ExpenseResponse> getByCashRegisterDetailId(int cashRegisterDetailId);

}
