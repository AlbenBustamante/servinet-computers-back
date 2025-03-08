package com.servinetcomputers.api.module.transaction.controller;

import com.servinetcomputers.api.module.transaction.application.usecase.GetAllTransactionsUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/transactions")
@RestController
public class TransactionController {
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAll() {
        return ResponseEntity.ok(getAllTransactionsUseCase.call());
    }
}
