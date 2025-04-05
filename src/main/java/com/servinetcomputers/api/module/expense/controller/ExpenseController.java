package com.servinetcomputers.api.module.expense.controller;

import com.servinetcomputers.api.module.expense.application.usecase.CreateExpenseUseCase;
import com.servinetcomputers.api.module.expense.application.usecase.DeleteExpenseUseCase;
import com.servinetcomputers.api.module.expense.application.usecase.UpdateExpenseUseCase;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.expense.domain.dto.UpdateExpenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/expenses")
@RestController
public class ExpenseController {
    private final CreateExpenseUseCase createExpenseUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;

    @PostMapping
    public ResponseEntity<ExpenseResponse> register(@RequestBody ExpenseRequest request) {
        return ResponseEntity.ok(createExpenseUseCase.call(request));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ExpenseResponse> update(@PathVariable("id") int expenseId, @RequestBody UpdateExpenseDto dto) {
        return ResponseEntity.ok(updateExpenseUseCase.call(expenseId, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int expenseId, @RequestParam("tempCode") int tempCode) {
        deleteExpenseUseCase.call(expenseId, tempCode);
        return ResponseEntity.noContent().build();
    }
}
