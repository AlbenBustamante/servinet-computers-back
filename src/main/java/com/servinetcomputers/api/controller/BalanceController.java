package com.servinetcomputers.api.controller;

import com.servinetcomputers.api.dto.request.BalanceRequest;
import com.servinetcomputers.api.dto.response.BalanceResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.service.IBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/balances")
@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final IBalanceService balanceService;

    @PostMapping
    public ResponseEntity<PageResponse<BalanceResponse>> register(@RequestBody BalanceRequest request) {
        return ResponseEntity.ok(balanceService.register(request));
    }

    @PatchMapping("/{balanceId}")
    public ResponseEntity<PageResponse<BalanceResponse>> update(@PathVariable("balanceId") int balanceId, @RequestBody BalanceRequest request) {
        return ResponseEntity.ok(balanceService.update(balanceId, request));
    }

    @DeleteMapping("/{balanceId}")
    public ResponseEntity<Boolean> delete(@PathVariable("balanceId") int balanceId) {
        return ResponseEntity.ok(balanceService.delete(balanceId));
    }

}
