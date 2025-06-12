package com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.api;

import com.servinetcomputers.api.module.base.Base;
import com.servinetcomputers.api.module.cashregister.application.port.in.command.CreateCashRegisterCommand;
import com.servinetcomputers.api.module.cashregister.application.port.in.command.UpdateCashRegisterCommand;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.CashRegisterRestAdapter;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/cash-registers")
@RestController
public class CashRegisterRestApi {
    private final CashRegisterRestAdapter adapter;

    @PostMapping
    public ResponseEntity<CashRegisterDto> register(@RequestBody CreateCashRegisterCommand command) {
        return ResponseEntity.ok(adapter.create(command));
    }

    @GetMapping
    public ResponseEntity<List<CashRegisterDto>> getAll() {
        return ResponseEntity.ok(adapter.getAll());
    }

    @GetMapping(path = "/{id}/lastBase")
    public ResponseEntity<Base> getLastBase(@PathVariable("id") int cashRegisterId) {
        return ResponseEntity.ok(adapter.getLastBase(cashRegisterId));
    }

    @GetMapping(path = "/{id}/movements")
    public ResponseEntity<List<CashRegisterDetailDto>> getMovements(@PathVariable("id") int cashRegisterId) {
        return ResponseEntity.ok(adapter.getMovements(cashRegisterId));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<CashRegisterDto> update(@PathVariable("id") int cashRegisterId, @RequestBody UpdateCashRegisterCommand command) {
        return ResponseEntity.ok(adapter.update(cashRegisterId, command));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int cashRegisterId) {
        adapter.delete(cashRegisterId);
        return ResponseEntity.ok().build();
    }
}
