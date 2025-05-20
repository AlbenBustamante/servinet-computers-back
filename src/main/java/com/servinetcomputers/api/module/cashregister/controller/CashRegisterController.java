package com.servinetcomputers.api.module.cashregister.controller;

import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.cashregister.application.usecase.*;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.UpdateCashRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/cash-registers")
@RestController
public class CashRegisterController {
    private final CreateCashRegisterUseCase createCashRegisterUseCase;
    private final GetAllCashRegistersUseCase getAllCashRegistersUseCase;
    private final GetLastBaseUseCase getLastBaseUseCase;
    private final GetLastDetailByIdUseCase getLastDetailByIdUseCase;
    private final GetAllMovementsUseCase getAllMovementsUseCase;
    private final UpdateCashRegisterUseCase updateCashRegisterUseCase;
    private final DeleteCashRegisterUseCase deleteCashRegisterUseCase;

    @PostMapping
    public ResponseEntity<CashRegisterDto> register(@RequestBody CreateCashRegisterDto request) {
        return ResponseEntity.ok(createCashRegisterUseCase.call(request));
    }

    @GetMapping
    public ResponseEntity<List<CashRegisterDto>> getAll() {
        return ResponseEntity.ok(getAllCashRegistersUseCase.call());
    }

    @GetMapping(path = "/{id}/lastBase")
    public ResponseEntity<BaseDto> getLastBase(@PathVariable("id") int cashRegisterId) {
        return ResponseEntity.ok(getLastBaseUseCase.call(cashRegisterId));
    }

    @GetMapping(path = "/{id}/lastDetail")
    public ResponseEntity<CashRegisterDetailDto> getLastDetail(@PathVariable("id") int cashRegisterId) {
        return ResponseEntity.ok(getLastDetailByIdUseCase.call(cashRegisterId));
    }

    @GetMapping(path = "/{id}/movements")
    public ResponseEntity<List<CashRegisterDetailDto>> getMovements(@PathVariable("id") int cashRegisterId) {
        return ResponseEntity.ok(getAllMovementsUseCase.call(cashRegisterId));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<CashRegisterDto> update(@PathVariable("id") int id, @RequestBody UpdateCashRegisterDto updateCashRegisterDto) {
        return ResponseEntity.ok(updateCashRegisterUseCase.call(id, updateCashRegisterDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterId) {
        deleteCashRegisterUseCase.call(cashRegisterId);
        return ResponseEntity.ok().build();
    }
}
