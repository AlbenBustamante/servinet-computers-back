package com.servinetcomputers.api.domain.cashregister.controller;

import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterBaseService;
import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseResponse;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cash-registers")
@RestController
public class CashRegisterController {
    private final ICashRegisterService service;
    private final ICashRegisterBaseService cashRegisterBaseService;

    @PostMapping
    public ResponseEntity<CashRegisterResponse> register(@RequestBody CashRegisterRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<CashRegisterResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}/lastBase")
    public ResponseEntity<CashRegisterBaseResponse> getLastBase(@PathVariable("id") int cashRegisterId) {
        return ResponseEntity.ok(cashRegisterBaseService.getLastBaseFromCashRegisterId(cashRegisterId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashRegisterResponse> update(@PathVariable("id") int cashRegisterId, @RequestBody CashRegisterRequest request) {
        return ResponseEntity.ok(service.update(cashRegisterId, request));
    }

    @PatchMapping
    public ResponseEntity<CashRegisterResponse> updateStatus(@RequestBody CashRegisterRequest request) {
        return ResponseEntity.ok(service.updateStatus(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterId) {
        return ResponseEntity.ok(service.delete(cashRegisterId));
    }
}
