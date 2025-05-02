package com.servinetcomputers.api.module.platform.controller;

import com.servinetcomputers.api.module.platform.application.usecase.balance.DeletePlatformBalanceUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.balance.UpdatePlatformBalanceUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.UpdatePlatformBalanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/platform-balances")
@RestController
@RequiredArgsConstructor
public class PlatformBalanceController {
    private final UpdatePlatformBalanceUseCase updatePlatformBalanceUseCase;
    private final DeletePlatformBalanceUseCase deletePlatformBalanceUseCase;

    @PatchMapping(path = "/{id}")
    public ResponseEntity<PlatformBalanceDto> update(@PathVariable("id") int balanceId, @RequestBody UpdatePlatformBalanceDto updatePlatformBalanceDto) {
        return ResponseEntity.ok(updatePlatformBalanceUseCase.call(balanceId, updatePlatformBalanceDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int balanceId) {
        deletePlatformBalanceUseCase.call(balanceId);
        return ResponseEntity.ok().build();
    }
}
