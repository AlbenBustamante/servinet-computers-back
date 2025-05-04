package com.servinetcomputers.api.module.expense.controller;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.expense.application.usecase.CreateExpenseUseCase;
import com.servinetcomputers.api.module.expense.application.usecase.DeleteExpenseUseCase;
import com.servinetcomputers.api.module.expense.application.usecase.UpdateExpenseUseCase;
import com.servinetcomputers.api.module.expense.domain.dto.CreateExpenseDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import com.servinetcomputers.api.module.expense.domain.dto.UpdateExpenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/expenses")
@RestController
public class ExpenseController {
    private final CreateExpenseUseCase createExpenseUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;

    @PostMapping
    public ResponseEntity<PageResponse<ExpenseDto>> register(
            @RequestBody CreateExpenseDto request,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(value = "property", defaultValue = "createdDate") String property
    ) {
        final var pageable = PageRequest.of(pageNumber, pageSize, direction, property);
        return ResponseEntity.ok(createExpenseUseCase.call(request, pageable));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ExpenseDto> update(@PathVariable("id") int expenseId, @RequestBody UpdateExpenseDto dto) {
        return ResponseEntity.ok(updateExpenseUseCase.call(expenseId, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int expenseId, @RequestParam("tempCode") int tempCode) {
        deleteExpenseUseCase.call(expenseId, tempCode);
        return ResponseEntity.noContent().build();
    }
}
