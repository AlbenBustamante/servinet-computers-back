package com.servinetcomputers.api.domain.platform.controller;

import com.servinetcomputers.api.domain.platform.abs.IPlatformService;
import com.servinetcomputers.api.domain.platform.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformResponse;
import com.servinetcomputers.api.domain.platform.dto.PortalPlatformDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The platform's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/platforms")
@RestController
public class PlatformController {

    private final IPlatformService platformService;

    @PostMapping
    public ResponseEntity<PlatformResponse> register(@RequestBody PlatformRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platformService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PlatformResponse>> getAll() {
        return ResponseEntity.ok(platformService.getAll());
    }

    @GetMapping("/portal")
    public ResponseEntity<List<PortalPlatformDto>> getAllPortalPlatforms() {
        return ResponseEntity.ok(platformService.getAllPortalPlatforms());
    }

    @PatchMapping("/{platformId}")
    public ResponseEntity<PlatformResponse> update(@PathVariable("platformId") int platformId, @RequestBody PlatformRequest request) {
        return ResponseEntity.ok(platformService.update(platformId, request));
    }

    @DeleteMapping("/{platformId}")
    public ResponseEntity<Boolean> delete(@PathVariable("platformId") int platformId) {
        return ResponseEntity.ok(platformService.delete(platformId));
    }

}
