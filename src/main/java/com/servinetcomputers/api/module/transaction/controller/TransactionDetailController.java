package com.servinetcomputers.api.module.transaction.controller;

import com.servinetcomputers.api.module.transaction.application.usecase.CreateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.application.usecase.DeleteTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.application.usecase.UpdateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.UpdateTransactionDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/transaction-details")
@RestController
public class TransactionDetailController {
    private final CreateTransactionDetailUseCase createTransactionDetailUseCase;
    private final UpdateTransactionDetailUseCase updateTransactionDetailUseCase;
    private final DeleteTransactionDetailUseCase deleteTransactionDetailUseCase;

    @PostMapping
    public ResponseEntity<TransactionDetailResponse> create(@RequestBody TransactionDetailRequest request) {
        return ResponseEntity.ok(createTransactionDetailUseCase.call(request));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<TransactionDetailResponse> update(@PathVariable("id") Integer transactionDetailId, @RequestBody UpdateTransactionDetailDto updateTransactionDetailDto) {
        return ResponseEntity.ok(updateTransactionDetailUseCase.call(transactionDetailId, updateTransactionDetailDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer transactionDetailId, @RequestParam("tempCode") Integer tempCode) {
        deleteTransactionDetailUseCase.call(transactionDetailId, tempCode);
        return ResponseEntity.noContent().build();
    }
}
