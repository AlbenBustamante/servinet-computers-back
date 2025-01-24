package com.servinetcomputers.api.domain.transaction.controller;

import com.servinetcomputers.api.domain.transaction.application.usecase.GetAllTransactionsUseCase;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionResponse;
import com.servinetcomputers.api.domain.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/transactions")
@RestController
public class TransactionController {
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;
    private final TransactionDetailRepository detailService;

    @PostMapping
    public ResponseEntity<TransactionDetailResponse> register(@RequestBody TransactionDetailRequest request) {
        return ResponseEntity.ok(detailService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAll() {
        return ResponseEntity.ok(getAllTransactionsUseCase.call());
    }
}
