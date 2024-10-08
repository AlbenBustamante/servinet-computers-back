package com.servinetcomputers.api.domain.platform.controller;

import com.servinetcomputers.api.domain.platform.abs.IPlatformBalanceService;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/platform-balances")
@RestController
@RequiredArgsConstructor
public class PlatformBalanceController {

    private final IPlatformBalanceService balanceService;

    @PatchMapping("/{balanceId}")
    public ResponseEntity<PlatformBalanceResponse> update(@PathVariable("balanceId") int balanceId, @RequestBody PlatformBalanceRequest request) {
        return ResponseEntity.ok(balanceService.update(balanceId, request));
    }

    @DeleteMapping("/{balanceId}")
    public ResponseEntity<Boolean> delete(@PathVariable("balanceId") int balanceId) {
        return ResponseEntity.ok(balanceService.delete(balanceId));
    }

}
