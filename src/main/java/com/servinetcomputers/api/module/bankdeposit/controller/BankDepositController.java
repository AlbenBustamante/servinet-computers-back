package com.servinetcomputers.api.module.bankdeposit.controller;

import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateBankDepositUseCase;
import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateDepositorUseCase;
import com.servinetcomputers.api.module.bankdeposit.application.usecase.GetBankDepositsBetweenUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateDepositorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/bank-deposits")
@RestController
public class BankDepositController {
    private final CreateBankDepositUseCase createBankDepositUseCase;
    private final CreateDepositorUseCase createDepositorUseCase;
    private final GetBankDepositsBetweenUseCase getBankDepositsBetweenUseCase;

    @PostMapping
    public ResponseEntity<BankDepositDto> create(@RequestBody CreateBankDepositDto createBankDepositDto) {
        return ResponseEntity.ok(createBankDepositUseCase.call(createBankDepositDto));
    }

    @PostMapping(path = "/enroll-depositor")
    public ResponseEntity<BankDepositDto> enrollDepositor(@RequestBody CreateDepositorDto createDepositorDto) {
        return ResponseEntity.ok(createDepositorUseCase.call(createDepositorDto));
    }

    @GetMapping
    public ResponseEntity<List<BankDepositDto>> getAllBetween(
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate
    ) {
        return ResponseEntity.ok(getBankDepositsBetweenUseCase.call(startDate, endDate));
    }
}
