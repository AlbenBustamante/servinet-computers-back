package com.servinetcomputers.api.module.safes.controller;

import com.servinetcomputers.api.module.base.Base;
import com.servinetcomputers.api.module.safes.application.port.in.CreateAdminTransferUseCase;
import com.servinetcomputers.api.module.safes.application.port.in.command.CreateAdminTransferCommand;
import com.servinetcomputers.api.module.safes.application.usecase.detail.LoadSafeDetailsOfDayUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.detail.UpdateSafeDetailBaseUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/safe-details")
@RestController
public class SafeDetailController {
    private final UpdateSafeDetailBaseUseCase updateSafeDetailBaseUseCase;
    private final CreateAdminTransferUseCase createAdminTransferUseCase;
    private final LoadSafeDetailsOfDayUseCase loadSafeDetailsOfDayUseCase;

    @GetMapping(path = "/load")
    public ResponseEntity<List<SafeDetailDto>> load() {
        return ResponseEntity.ok(loadSafeDetailsOfDayUseCase.call());
    }

    @PutMapping(path = "/{id}/base")
    public ResponseEntity<SafeDetailDto> updateBase(@PathVariable("id") int safeDetailId, @RequestBody Base base) {
        return ResponseEntity.ok(updateSafeDetailBaseUseCase.call(safeDetailId, base));
    }

    @PutMapping(path = "/{id}/transfer")
    public ResponseEntity<SafeDetailDto> createTransfer(@PathVariable("id") int safeDetailId, @RequestBody CreateAdminTransferCommand command) {
        return ResponseEntity.ok(createAdminTransferUseCase.create(safeDetailId, command));
    }
}
