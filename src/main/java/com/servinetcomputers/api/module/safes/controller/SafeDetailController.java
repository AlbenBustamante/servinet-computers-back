package com.servinetcomputers.api.module.safes.controller;

import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.safes.application.usecase.detail.LoadSafeDetailsOfDayUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.detail.UpdateSafeDetailBaseUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/safe-details")
@RestController
public class SafeDetailController {
    private final UpdateSafeDetailBaseUseCase updateSafeDetailBaseUseCase;
    private final LoadSafeDetailsOfDayUseCase loadSafeDetailsOfDayUseCase;

    @GetMapping(path = "/load")
    public ResponseEntity<List<SafeDetailDto>> load() {
        return ResponseEntity.ok(loadSafeDetailsOfDayUseCase.call());
    }

    @PutMapping(path = "/{id}/base")
    public ResponseEntity<SafeDetailDto> updateBase(@PathVariable("id") int safeDetailId, @RequestBody BaseDto baseDto) {
        return ResponseEntity.ok(updateSafeDetailBaseUseCase.call(safeDetailId, baseDto));
    }
}
