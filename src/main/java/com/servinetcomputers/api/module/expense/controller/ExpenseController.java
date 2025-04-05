package com.servinetcomputers.api.module.expense.controller;

import com.servinetcomputers.api.module.expense.application.usecase.CreateExpenseUseCase;
import com.servinetcomputers.api.module.expense.application.usecase.DeleteExpenseUseCase;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/expenses")
@RestController
public class ExpenseController {
    private final CreateExpenseUseCase createExpenseUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;

    @PostMapping
    public ResponseEntity<ExpenseResponse> register(@RequestBody ExpenseRequest request) {
        return ResponseEntity.ok(createExpenseUseCase.call(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int expenseId, @RequestParam("tempCode") int tempCode) {
        deleteExpenseUseCase.call(expenseId, tempCode);
        return ResponseEntity.noContent().build();
    }
}
