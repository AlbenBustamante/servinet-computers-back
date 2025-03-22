package com.servinetcomputers.api.module.cashregister.controller;

import com.servinetcomputers.api.module.cashregister.application.usecase.detail.*;
import com.servinetcomputers.api.module.cashregister.domain.dto.*;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/cash-register-details")
@RestController
public class CashRegisterDetailController {
    private final CreateCashRegisterDetailUseCase createCashRegisterDetailUseCase;
    private final CashRegisterDetailAlreadyExistsUseCase alreadyExistsUseCase;
    private final GetAdmCashRegisterDetailsUseCase getAdmCashRegisterDetailsUseCase;
    private final GetCashRegisterDetailByIdUseCase getByIdUseCase;
    private final GetCashRegisterDetailReportsByIdUseCase getReportsUseCase;
    private final GetCashTransfersByIdUseCase getCashTransfersUseCase;
    private final GetDetailedReportsByIdUseCase getDetailedReportsByIdUseCase;
    private final StartBreakUseCase startBreakUseCase;
    private final EndBreakUseCase endBreakUseCase;
    private final CloseUseCase closeUseCase;
    private final DeleteCashRegisterDetailUseCase deleteUseCase;
    private final GetExpensesUseCase getExpensesUseCase;
    private final GetTransactionsUseCase getTransactionsUseCase;

    @PostMapping
    public ResponseEntity<MyCashRegistersReports> register(@RequestBody CreateCashRegisterDetailDto request) {
        return ResponseEntity.ok(createCashRegisterDetailUseCase.call(request));
    }

    @GetMapping(path = "/already-exists")
    public ResponseEntity<AlreadyExistsCashRegisterDetailDto> alreadyExists() {
        return ResponseEntity.ok(alreadyExistsUseCase.call());
    }

    @GetMapping(path = "/adm")
    public ResponseEntity<AdmCashRegistersDto> getAdmCashRegisterDetails() {
        return ResponseEntity.ok(getAdmCashRegisterDetailsUseCase.call());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CashRegisterDetailResponse> getById(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(getByIdUseCase.call(cashRegisterDetailId));
    }

    @GetMapping(path = "/{id}/detailed-reports")
    public ResponseEntity<DetailedCashRegisterReportsDto> getDetailedReports(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(getDetailedReportsByIdUseCase.call(cashRegisterDetailId));
    }

    @GetMapping(path = "/{id}/reports")
    public ResponseEntity<CashRegisterDetailReportsDto> getReports(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(getReportsUseCase.call(cashRegisterDetailId));
    }

    @GetMapping(path = "/{id}/transactions")
    public ResponseEntity<List<TransactionDetailResponse>> getTransactions(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(getTransactionsUseCase.call(cashRegisterDetailId));
    }

    @GetMapping(path = "/{id}/expenses")
    public ResponseEntity<List<ExpenseResponse>> getExpenses(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(getExpensesUseCase.call(cashRegisterDetailId));
    }

    @GetMapping(path = "/{id}/cash-transfers")
    public ResponseEntity<List<CashTransferDto>> getCashTransfers(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(getCashTransfersUseCase.call(cashRegisterDetailId));
    }

    @PutMapping(path = "/{id}/start-break")
    public ResponseEntity<CashRegisterDetailResponse> startBreak(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(startBreakUseCase.call(cashRegisterDetailId));
    }

    @PutMapping(path = "/{id}/end-break")
    public ResponseEntity<CashRegisterDetailResponse> endBreak(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(endBreakUseCase.call(cashRegisterDetailId));
    }

    @PutMapping(path = "/{id}/close")
    public ResponseEntity<CashRegisterDetailReportsDto> close(@PathVariable("id") int id, @RequestBody CloseCashRegisterDetailDto closeCashRegisterDetail) {
        return ResponseEntity.ok(closeUseCase.call(id, closeCashRegisterDetail));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterDetailId) {
        deleteUseCase.call(cashRegisterDetailId);
        return ResponseEntity.ok().build();
    }
}
