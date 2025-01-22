package com.servinetcomputers.api.domain.platform.controller;

import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/platform-balances")
@RestController
@RequiredArgsConstructor
public class PlatformBalanceController {

    private final PlatformBalanceRepository balanceService;

    @PatchMapping("/{balanceId}")
    public ResponseEntity<PlatformBalanceResponse> update(@PathVariable("balanceId") int balanceId, @RequestBody PlatformBalanceRequest request) {
        return ResponseEntity.ok(balanceService.update(balanceId, request));
    }

    @DeleteMapping("/{balanceId}")
    public ResponseEntity<Boolean> delete(@PathVariable("balanceId") int balanceId) {
        return ResponseEntity.ok(balanceService.delete(balanceId));
    }

}
