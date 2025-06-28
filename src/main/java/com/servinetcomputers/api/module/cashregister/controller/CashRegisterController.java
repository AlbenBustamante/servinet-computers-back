package com.servinetcomputers.api.module.cashregister.controller;

import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.cashregister.application.usecase.CreateCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.DeleteCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.GetAllCashRegistersUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.GetAllMovementsUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.GetLastBaseUseCase;
import com.servinetcomputers.api.module.cashregister.application.usecase.UpdateCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.UpdateCashRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/cash-registers")
@RestController
public class CashRegisterController {
    private final CreateCashRegisterUseCase createCashRegisterUseCase;
    private final GetAllCashRegistersUseCase getAllCashRegistersUseCase;
    private final GetLastBaseUseCase getLastBaseUseCase;
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

    @GetMapping(path = "/{id}/movements")
    public ResponseEntity<List<CashRegisterDetailDto>> getMovements(@PathVariable("id") int cashRegisterId, @RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(getAllMovementsUseCase.call(cashRegisterId, date));
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
