package com.servinetcomputers.api.domain.safes.controller;

import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeResponse;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/safes")
@RestController
@RequiredArgsConstructor
public class SafeController {

    private final SafeRepository service;
    private final SafeDetailRepository detailService;

    @PostMapping
    public ResponseEntity<SafeResponse> register(@RequestBody SafeRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<SafeResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/load-details")
    public ResponseEntity<List<SafeDetailResponse>> loadDetails() {
        return ResponseEntity.ok(detailService.loadSafes());
    }

    @PutMapping("/update-base")
    public ResponseEntity<SafeDetailResponse> updateBase(@RequestBody SafeBaseRequest request) {
        return ResponseEntity.ok(detailService.updateBase(request));
    }

}
