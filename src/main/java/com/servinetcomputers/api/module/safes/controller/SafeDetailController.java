package com.servinetcomputers.api.module.safes.controller;

import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.safes.application.usecase.detail.GetSafeDetailMovementsByIdUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.detail.LoadSafeDetailsOfDayUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.detail.UpdateSafeDetailBaseUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.module.safes.domain.dto.SafeMovementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/safe-details")
@RestController
public class SafeDetailController {
    private final UpdateSafeDetailBaseUseCase updateSafeDetailBaseUseCase;
    private final LoadSafeDetailsOfDayUseCase loadSafeDetailsOfDayUseCase;
    private final GetSafeDetailMovementsByIdUseCase getMovementsUseCase;

    @GetMapping(path = "/load")
    public ResponseEntity<List<SafeDetailResponse>> load() {
        return ResponseEntity.ok(loadSafeDetailsOfDayUseCase.call());
    }

    @GetMapping(path = "/{id}/movements")
    public ResponseEntity<SafeMovementDto> getMovements(@PathVariable("id") int safeDetailId) {
        return ResponseEntity.ok(getMovementsUseCase.call(safeDetailId));
    }

    @PutMapping(path = "/{id}/base")
    public ResponseEntity<SafeDetailResponse> updateBase(@PathVariable("id") int safeDetailId, @RequestBody BaseDto baseDto) {
        return ResponseEntity.ok(updateSafeDetailBaseUseCase.call(safeDetailId, baseDto));
    }
}
