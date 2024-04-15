package com.servinetcomputers.api.domain.cashregister.controller;

import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterBaseService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cash-register-bases")
@RestController
public class CashRegisterBaseController {
    private final ICashRegisterBaseService service;

    @PostMapping
    public ResponseEntity<CashRegisterBaseResponse> register(@RequestBody CashRegisterBaseRequest request) {
        return ResponseEntity.ok(service.create(request));
    }
}
