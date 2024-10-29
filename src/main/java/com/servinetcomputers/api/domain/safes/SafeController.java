package com.servinetcomputers.api.domain.safes;

import com.servinetcomputers.api.domain.safes.abs.ISafeBaseService;
import com.servinetcomputers.api.domain.safes.abs.ISafeService;
import com.servinetcomputers.api.domain.safes.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeBaseResponse;
import com.servinetcomputers.api.domain.safes.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/safes")
@RestController
@RequiredArgsConstructor
public class SafeController {

    private final ISafeService service;
    private final ISafeBaseService baseService;

    @PostMapping
    public ResponseEntity<SafeResponse> register(@RequestBody SafeRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<SafeResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/{id}/base")
    public ResponseEntity<SafeBaseResponse> addBase(@RequestBody SafeBaseRequest request) {
        return ResponseEntity.ok(baseService.create(request));
    }

}
