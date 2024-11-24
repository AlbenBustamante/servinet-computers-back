package com.servinetcomputers.api.domain.safes.controller;

import com.servinetcomputers.api.domain.safes.abs.ISafeDetailService;
import com.servinetcomputers.api.domain.safes.abs.ISafeService;
import com.servinetcomputers.api.domain.safes.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/safes")
@RestController
@RequiredArgsConstructor
public class SafeController {

    private final ISafeService service;
    private final ISafeDetailService detailService;

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
