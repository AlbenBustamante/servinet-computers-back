package com.servinetcomputers.api.module.cashregister.controller;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.CashRegisterDetailAlreadyExistsUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.CloseUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.CreateCashRegisterDetailUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.DeleteCashRegisterDetailUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.EndBreakUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashRegisterDetailByIdUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashRegisterDetailReportsAndMovementsUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashRegisterDetailReportsByIdUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetCashTransfersByIdUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetExpensesUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.GetTransactionsUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.StartBreakUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.detail.UpdateCashRegisterDetailBaseUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.AlreadyExistsCashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailMovementsDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CloseCashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.MyCashRegistersReports;
import com.servinetcomputers.api.module.cashregister.domain.dto.UpdateCashRegisterDetailBaseDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/cash-register-details")
@RestController
public class CashRegisterDetailController {
    private final CreateCashRegisterDetailUseCase createCashRegisterDetailUseCase;
    private final CashRegisterDetailAlreadyExistsUseCase alreadyExistsUseCase;
    private final GetCashRegisterDetailByIdUseCase getByIdUseCase;
    private final GetCashRegisterDetailReportsByIdUseCase getReportsUseCase;
    private final GetCashTransfersByIdUseCase getCashTransfersUseCase;
    private final GetCashRegisterDetailReportsAndMovementsUseCase getDetailedReportsUseCase;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<CashRegisterDetailDto> getById(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(getByIdUseCase.call(cashRegisterDetailId));
    }

    @GetMapping(path = "/{id}/reports-and-movements")
    public ResponseEntity<CashRegisterDetailMovementsDto> getReportsAndMovements(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(getDetailedReportsUseCase.call(cashRegisterDetailId));
    }

    @GetMapping(path = "/{id}/reports")
    public ResponseEntity<CashRegisterDetailReportsDto> getReports(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(getReportsUseCase.call(cashRegisterDetailId));
    }

    @GetMapping(path = "/{id}/transactions")
    public ResponseEntity<PageResponse<TransactionDetailDto>> getTransactions(
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
    public ResponseEntity<PageResponse<ExpenseDto>> getExpenses(
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
    public ResponseEntity<CashRegisterDetailDto> startBreak(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(startBreakUseCase.call(cashRegisterDetailId));
    }

    @PutMapping(path = "/{id}/end-break")
    public ResponseEntity<CashRegisterDetailDto> endBreak(@PathVariable("id") int cashRegisterDetailId) {
        return ResponseEntity.ok(endBreakUseCase.call(cashRegisterDetailId));
    }

    @PutMapping(path = "/{id}/close")
    public ResponseEntity<CashRegisterDetailReportsDto> close(@PathVariable("id") int id, @RequestBody CloseCashRegisterDetailDto closeCashRegisterDetail) {
        return ResponseEntity.ok(closeUseCase.call(id, closeCashRegisterDetail));
    }

    @PutMapping(path = "/{id}/update-base")
    public ResponseEntity<CashRegisterDetailDto> updateBase(@PathVariable("id") int id, @RequestBody UpdateCashRegisterDetailBaseDto dto) {
        return ResponseEntity.ok(updateCashRegisterDetailBaseUseCase.call(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterDetailId) {
        deleteUseCase.call(cashRegisterDetailId);
        return ResponseEntity.ok().build();
    }
}
