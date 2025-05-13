package com.servinetcomputers.api.module.bankdeposit.controller;

import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateBankDepositPaymentUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositPaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/bank-deposit-payments")
@RestController
public class BankDepositPaymentController {
    private final CreateBankDepositPaymentUseCase createBankDepositPaymentUseCase;

    @PostMapping
    public ResponseEntity<BankDepositDto> create(@RequestBody CreateBankDepositPaymentDto dto) {
        return ResponseEntity.ok(createBankDepositPaymentUseCase.call(dto));
    }
}
