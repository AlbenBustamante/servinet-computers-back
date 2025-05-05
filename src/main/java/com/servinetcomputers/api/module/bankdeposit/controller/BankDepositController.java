package com.servinetcomputers.api.module.bankdeposit.controller;

import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateBankDepositUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/bank-deposits")
@RestController
public class BankDepositController {
    private final CreateBankDepositUseCase createBankDepositUseCase;

    @PostMapping
    public ResponseEntity<BankDepositDto> create(@RequestBody CreateBankDepositDto createBankDepositDto) {
        return ResponseEntity.ok(createBankDepositUseCase.call(createBankDepositDto));
    }
}
