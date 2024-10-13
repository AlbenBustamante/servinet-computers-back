package com.servinetcomputers.api.domain.expense.abs;

import com.servinetcomputers.api.domain.expense.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.dto.ExpenseResponse;

public interface IExpenseService {

    ExpenseResponse create(ExpenseRequest request);

}
