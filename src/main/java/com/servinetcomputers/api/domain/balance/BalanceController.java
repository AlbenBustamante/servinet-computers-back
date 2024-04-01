package com.servinetcomputers.api.domain.balance;

import com.servinetcomputers.api.domain.PageResponse;
import com.servinetcomputers.api.domain.balance.abs.IBalanceService;
import com.servinetcomputers.api.domain.balance.model.dto.BalanceRequest;
import com.servinetcomputers.api.domain.balance.model.dto.BalanceResponse;
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
