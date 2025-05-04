package com.servinetcomputers.api.module.tempcode.controller;

import com.servinetcomputers.api.module.tempcode.application.usecase.LoadTempCodeUseCase;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/temp_codes")
@RestController
public class TempCodeController {
    private final LoadTempCodeUseCase loadTempCodeUseCase;

    @GetMapping(path = "/load")
    public ResponseEntity<TempCodeDto> loadTempCode() {
        return ResponseEntity.ok(loadTempCodeUseCase.call());
    }
}
