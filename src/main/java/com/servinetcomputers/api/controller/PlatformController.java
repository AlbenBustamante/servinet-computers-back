package com.servinetcomputers.api.controller;

import com.servinetcomputers.api.dto.request.PlatformRequest;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.PlatformResponse;
import com.servinetcomputers.api.service.IPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The platform's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/platforms")
@RestController
public class PlatformController {

    private final IPlatformService platformService;

    @PostMapping
    public ResponseEntity<PageResponse<PlatformResponse>> register(@RequestBody PlatformRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platformService.create(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<PlatformResponse>> getAll() {
        return ResponseEntity.ok(platformService.getAll());
    }

    @PatchMapping("/{platformId}")
    public ResponseEntity<PageResponse<PlatformResponse>> update(@PathVariable("platformId") int platformId, @RequestBody PlatformRequest request) {
        return ResponseEntity.ok(platformService.update(platformId, request));
    }

    @DeleteMapping("/{platformId}")
    public ResponseEntity<Boolean> delete(@PathVariable("platformId") int platformId) {
        return ResponseEntity.ok(platformService.delete(platformId));
    }

}
