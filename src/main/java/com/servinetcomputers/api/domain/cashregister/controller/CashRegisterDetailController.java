package com.servinetcomputers.api.domain.cashregister.controller;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterDetailService;
import com.servinetcomputers.api.domain.cashregister.dto.*;
import com.servinetcomputers.api.domain.expense.abs.IExpenseService;
import com.servinetcomputers.api.domain.expense.dto.ExpenseResponse;
import com.servinetcomputers.api.domain.transaction.abs.ITransactionDetailService;
import com.servinetcomputers.api.domain.transaction.dto.TransactionDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cash-register-details")
@RestController
public class CashRegisterDetailController {
    private final ICashRegisterDetailService service;
    private final IExpenseService expenseService;
    private final ITransactionDetailService transactionDetailService;

    @PostMapping
    public ResponseEntity<MyCashRegistersReports> register(@RequestBody CashRegisterDetailRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/already-exists")
    public ResponseEntity<AlreadyExistsCashRegisterDetailDto> alreadyExists() {
        return ResponseEntity.ok(service.alreadyExists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashRegisterDetailResponse> getById(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.getById(cashRegisterDetailId));
    }

    @GetMapping("/{id}/reports")
    public ResponseEntity<CashRegisterDetailReportsDto> getReports(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(service.getCashRegisterDetailReports(cashRegisterDetailId));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDetailResponse>> getTransactions(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(transactionDetailService.getByCashRegisterDetailId(cashRegisterDetailId));
    }

    @GetMapping("/{id}/expenses")
    public ResponseEntity<List<ExpenseResponse>> getExpenses(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(expenseService.getByCashRegisterDetailId(cashRegisterDetailId));
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
