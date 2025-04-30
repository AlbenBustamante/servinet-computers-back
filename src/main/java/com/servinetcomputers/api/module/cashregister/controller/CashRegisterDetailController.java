package com.servinetcomputers.api.module.cashregister.controller;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.*;
import com.servinetcomputers.api.module.cashregister.domain.dto.*;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final UpdateCashRegisterDetailBaseUseCase updateCashRegisterDetailBaseUseCase;

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
    public ResponseEntity<PageResponse<TransactionDetailResponse>> getTransactions(
            @PathVariable("id") int cashRegisterDetailId,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(value = "property", defaultValue = "createdDate") String property
    ) {
        final var pageable = PageRequest.of(pageNumber, pageSize, direction, property);
        return ResponseEntity.ok(getTransactionsUseCase.call(cashRegisterDetailId, pageable));
    }

    @GetMapping(path = "/{id}/expenses")
    public ResponseEntity<PageResponse<ExpenseResponse>> getExpenses(
            @PathVariable("id") int cashRegisterDetailId,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(value = "property", defaultValue = "createdDate") String property
    ) {
        final var pageable = PageRequest.of(pageNumber, pageSize, direction, property);
        return ResponseEntity.ok(getExpensesUseCase.call(cashRegisterDetailId, pageable));
    }

    @GetMapping(path = "/{id}/cash-transfers")
    public ResponseEntity<PageResponse<CashTransferDto>> getCashTransfers(
            @PathVariable("id") int cashRegisterDetailId,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(value = "property", defaultValue = "createdDate") String property
    ) {
        final var pageable = PageRequest.of(pageNumber, pageSize, direction, property);
        return ResponseEntity.ok(getCashTransfersUseCase.call(cashRegisterDetailId, pageable));
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

    @PutMapping(path = "/{id}/update-base")
    public ResponseEntity<CashRegisterDetailResponse> updateBase(@PathVariable("id") int id, @RequestBody UpdateCashRegisterDetailBaseDto dto) {
        return ResponseEntity.ok(updateCashRegisterDetailBaseUseCase.call(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterDetailId) {
        deleteUseCase.call(cashRegisterDetailId);
        return ResponseEntity.ok().build();
    }
}
