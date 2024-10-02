package com.servinetcomputers.api.domain.cashregister.controller;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterDetailService;
import com.servinetcomputers.api.domain.cashregister.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cash-register-details")
@RestController
public class CashRegisterDetailController {
    private final ICashRegisterDetailService service;

    @PostMapping
    public ResponseEntity<MyCashRegistersReports> register(@RequestBody CashRegisterDetailRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashRegisterDetailResponse> getById(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.getById(cashRegisterDetailId));
    }

    @GetMapping("/already-exists")
    public ResponseEntity<AlreadyExistsCashRegisterDetailDto> alreadyExists() {
        return ResponseEntity.ok(service.alreadyExists());
    }

    @PatchMapping("/{id}/start-break")
    public ResponseEntity<CashRegisterDetailResponse> startBreak(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.startBrake(cashRegisterDetailId));
    }

    @PatchMapping("/{id}/end-break")
    public ResponseEntity<CashRegisterDetailResponse> endBrake(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.endBrake(cashRegisterDetailId));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<CashRegisterDetailReportsDto> close(@PathVariable("id") int cashRegisterDetailId, @RequestBody BaseDto finalBase) {
        return ResponseEntity.ok(service.close(cashRegisterDetailId, finalBase));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.delete(cashRegisterDetailId));
    }
}
