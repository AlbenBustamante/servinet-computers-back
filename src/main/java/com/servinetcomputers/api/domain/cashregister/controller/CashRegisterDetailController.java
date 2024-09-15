package com.servinetcomputers.api.domain.cashregister.controller;

import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterDetailService;
import com.servinetcomputers.api.domain.cashregister.dto.AlreadyExistsCashRegisterDetailDto;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailReportsDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<CashRegisterDetailResponse> getById(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.getById(cashRegisterDetailId));
    }

    @GetMapping("/{id}/reports")
    public ResponseEntity<CashRegisterDetailReportsDto> getReports(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.getReports(cashRegisterDetailId));
    }

    @GetMapping("/already-exists")
    public ResponseEntity<AlreadyExistsCashRegisterDetailDto> alreadyExists() {
        return ResponseEntity.ok(service.alreadyExists());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashRegisterDetailResponse> updateHours(@PathVariable("id") int cashRegisterDetailId, @RequestBody CashRegisterDetailRequest request) {
        return ResponseEntity.ok(service.updateHours(cashRegisterDetailId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.delete(cashRegisterDetailId));
    }
}
