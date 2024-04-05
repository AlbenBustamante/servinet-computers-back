package com.servinetcomputers.api.domain.cashregister;

import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cash-registers")
@RestController
public class CashRegisterController {
    private final ICashRegisterService service;

    @PostMapping
    public ResponseEntity<CashRegisterResponse> register(@RequestBody CashRegisterRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{enabled}")
    public ResponseEntity<List<CashRegisterResponse>> getAll(@PathVariable("enabled") boolean enabled) {
        return ResponseEntity.ok(service.getAll(enabled));
    }

    @PutMapping
    public ResponseEntity<CashRegisterResponse> updateStatus(@RequestBody CashRegisterRequest request) {
        return ResponseEntity.ok(service.updateStatus(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashRegisterId) {
        return ResponseEntity.ok(service.delete(cashRegisterId));
    }
}
