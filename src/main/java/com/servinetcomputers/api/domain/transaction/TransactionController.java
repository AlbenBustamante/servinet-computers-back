package com.servinetcomputers.api.domain.transaction;

import com.servinetcomputers.api.domain.transaction.abs.ITransactionService;
import com.servinetcomputers.api.domain.transaction.dto.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private final ITransactionService service;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

}
