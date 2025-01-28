package com.servinetcomputers.api.module.cashregister.controller;

import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.cashregister.application.usecase.*;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterRequest;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterResponse;
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
    private final UpdateCashRegisterUseCase updateCashRegisterUseCase;
    private final DeleteCashRegisterUseCase deleteCashRegisterUseCase;

    @PostMapping
    public ResponseEntity<CashRegisterResponse> register(@RequestBody CashRegisterRequest request) {
        return ResponseEntity.ok(createCashRegisterUseCase.call(request));
    }

    @GetMapping
    public ResponseEntity<List<CashRegisterResponse>> getAll() {
        return ResponseEntity.ok(getAllCashRegistersUseCase.call());
    }

    @GetMapping(path = "/{id}/lastBase")
    public ResponseEntity<BaseDto> getLastBase(@PathVariable("id") int cashRegisterId) {
        return ResponseEntity.ok(getLastBaseUseCase.call(cashRegisterId));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<CashRegisterResponse> update(@PathVariable("id") int id, @RequestBody UpdateCashRegisterDto updateCashRegisterDto) {
        return ResponseEntity.ok(updateCashRegisterUseCase.call(id, updateCashRegisterDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterId) {
        deleteCashRegisterUseCase.call(cashRegisterId);
        return ResponseEntity.ok().build();
    }
}
