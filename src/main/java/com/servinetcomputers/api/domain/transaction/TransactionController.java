package com.servinetcomputers.api.domain.transaction;

import com.servinetcomputers.api.domain.transaction.abs.ITransactionDetailService;
import com.servinetcomputers.api.domain.transaction.abs.ITransactionService;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private final ITransactionService service;
    private final ITransactionDetailService detailService;

    @PostMapping
    public ResponseEntity<TransactionDetailResponse> register(@RequestBody TransactionDetailRequest request) {
        return ResponseEntity.ok(detailService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllDescriptions() {
        return ResponseEntity.ok(service.getAllDescriptions());
    }

}
