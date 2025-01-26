package com.servinetcomputers.api.domain.safes.controller;

import com.servinetcomputers.api.domain.safes.application.usecase.CreateSafeUseCase;
import com.servinetcomputers.api.domain.safes.application.usecase.GetAllSafesUseCase;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeResponse;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeDetailRepository;
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
    private final SafeDetailRepository detailService;

    @PostMapping
    public ResponseEntity<SafeResponse> register(@RequestBody SafeRequest request) {
        return ResponseEntity.ok(createSafeUseCase.call(request));
    }

    @GetMapping
    public ResponseEntity<List<SafeResponse>> getAll() {
        return ResponseEntity.ok(getAllSafesUseCase.call());
    }

    @GetMapping(path = "/load-details")
    public ResponseEntity<List<SafeDetailResponse>> loadDetails() {
        return ResponseEntity.ok(detailService.loadSafes());
    }
}
