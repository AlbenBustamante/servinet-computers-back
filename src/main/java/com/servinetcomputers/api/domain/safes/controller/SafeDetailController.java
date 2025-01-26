package com.servinetcomputers.api.domain.safes.controller;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.safes.application.usecase.detail.LoadSafesOfDayUseCase;
import com.servinetcomputers.api.domain.safes.application.usecase.detail.UpdateSafeDetailBaseUseCase;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/safe-details")
@RestController
public class SafeDetailController {
    private final UpdateSafeDetailBaseUseCase updateSafeDetailBaseUseCase;
    private final LoadSafesOfDayUseCase loadSafesOfDayUseCase;

    @GetMapping(path = "/load")
    public ResponseEntity<List<SafeDetailResponse>> load() {
        return ResponseEntity.ok(loadSafesOfDayUseCase.call());
    }

    @PutMapping(path = "/{id}/base")
    public ResponseEntity<SafeDetailResponse> updateBase(@PathVariable("id") int safeDetailId, @RequestBody BaseDto baseDto) {
        return ResponseEntity.ok(updateSafeDetailBaseUseCase.call(safeDetailId, baseDto));
    }
}
