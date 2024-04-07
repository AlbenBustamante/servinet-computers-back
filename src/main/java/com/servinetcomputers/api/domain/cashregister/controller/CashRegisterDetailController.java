package com.servinetcomputers.api.domain.cashregister.controller;

import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterDetailService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailReq;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cash-register-details")
@RestController
public class CashRegisterDetailController {
    private final ICashRegisterDetailService service;

    @PostMapping
    public ResponseEntity<CashRegisterDetailRes> register(@RequestBody CashRegisterDetailReq request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/already-exists")
    public ResponseEntity<Boolean> isAlreadyCreated() {
        return ResponseEntity.ok(service.isAlreadyCreated());
    }

    @PutMapping
    public ResponseEntity<CashRegisterDetailRes> updateHours(@RequestBody CashRegisterDetailReq request) {
        return ResponseEntity.ok(service.updateHours(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.delete(cashRegisterDetailId));
    }
}
