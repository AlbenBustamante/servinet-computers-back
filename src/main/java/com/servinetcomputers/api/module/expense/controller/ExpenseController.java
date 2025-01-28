package com.servinetcomputers.api.module.expense.controller;

import com.servinetcomputers.api.module.expense.application.usecase.CreateExpenseUseCase;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/expenses")
@RestController
public class ExpenseController {
    private final CreateExpenseUseCase createExpenseUseCase;

    @PostMapping
    public ResponseEntity<ExpenseResponse> register(@RequestBody ExpenseRequest request) {
        return ResponseEntity.ok(createExpenseUseCase.call(request));
    }
}
