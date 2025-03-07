package com.servinetcomputers.api.module.transaction.controller;

import com.servinetcomputers.api.module.transaction.application.usecase.CreateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.application.usecase.UpdateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.UpdateTransactionDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/transaction-details")
public class TransactionDetailController {
    private final CreateTransactionDetailUseCase createTransactionDetailUseCase;
    private final UpdateTransactionDetailUseCase updateTransactionDetailUseCase;

    @PostMapping
    public ResponseEntity<TransactionDetailResponse> create(@RequestBody TransactionDetailRequest request) {
        return ResponseEntity.ok(createTransactionDetailUseCase.call(request));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<TransactionDetailResponse> update(@PathVariable("id") Integer transactionDetailId, @RequestBody UpdateTransactionDetailDto updateTransactionDetailDto) {
        return ResponseEntity.ok(updateTransactionDetailUseCase.call(transactionDetailId, updateTransactionDetailDto));
    }
}
