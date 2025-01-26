package com.servinetcomputers.api.domain.safes.controller;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.safes.application.usecase.detail.UpdateSafeDetailBaseUseCase;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/safe-details")
@RestController
public class SafeDetailController {
    private final UpdateSafeDetailBaseUseCase updateSafeDetailBaseUseCase;

    @PutMapping(path = "/{id}/base")
    public ResponseEntity<SafeDetailResponse> updateBase(@PathVariable("id") int safeDetailId, @RequestBody BaseDto baseDto) {
        return ResponseEntity.ok(updateSafeDetailBaseUseCase.call(safeDetailId, baseDto));
    }
}
