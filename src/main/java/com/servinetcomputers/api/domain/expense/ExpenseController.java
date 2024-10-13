package com.servinetcomputers.api.domain.expense;

import com.servinetcomputers.api.domain.expense.abs.IExpenseService;
import com.servinetcomputers.api.domain.expense.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.dto.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/expenses")
@RestController
public class ExpenseController {

    private final IExpenseService service;

    @PostMapping
    public ResponseEntity<ExpenseResponse> register(@RequestBody ExpenseRequest request) {
        return ResponseEntity.ok(service.create(request));
    }
    
}
