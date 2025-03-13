package com.servinetcomputers.api.module.safes.controller;

import com.servinetcomputers.api.module.safes.application.usecase.CreateSafeUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.GetAllSafeDetailsByIdUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.GetAllSafesUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.module.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/safes")
@RestController
public class SafeController {
    private final CreateSafeUseCase createSafeUseCase;
    private final GetAllSafesUseCase getAllSafesUseCase;
    private final GetAllSafeDetailsByIdUseCase getDetailsUseCase;

    @PostMapping
    public ResponseEntity<SafeResponse> register(@RequestBody SafeRequest request) {
        return ResponseEntity.ok(createSafeUseCase.call(request));
    }

    @GetMapping
    public ResponseEntity<List<SafeResponse>> getAll() {
        return ResponseEntity.ok(getAllSafesUseCase.call());
    }

    @GetMapping(path = "/{id}/details")
    public ResponseEntity<List<SafeDetailResponse>> getDetails(@PathVariable("id") int safeId) {
        return ResponseEntity.ok(getDetailsUseCase.call(safeId));
    }
}
