package com.servinetcomputers.api.domain.cashregister.controller;

import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterDetailService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cash-register-details")
@RestController
public class CashRegisterDetailController {
    private final ICashRegisterDetailService service;

    @PostMapping
    public ResponseEntity<CashRegisterDetailResponse> register(@RequestBody CashRegisterDetailRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<CashRegisterDetailResponse> get() {
        return ResponseEntity.ok(service.get());
    }

    @GetMapping("/already-exists")
    public ResponseEntity<Boolean> isAlreadyCreated() {
        return ResponseEntity.ok(service.isAlreadyCreated());
    }

    @PutMapping
    public ResponseEntity<CashRegisterDetailResponse> updateHours(@RequestBody CashRegisterDetailRequest request) {
        return ResponseEntity.ok(service.updateHours(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.delete(cashRegisterDetailId));
    }
}
