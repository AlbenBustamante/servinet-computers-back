package com.servinetcomputers.api.controller;

import com.servinetcomputers.api.dto.request.PlatformRequest;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.PlatformResponse;
import com.servinetcomputers.api.service.IPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PatchMapping("/{platformId}")
    public ResponseEntity<PageResponse<PlatformResponse>> update(@PathVariable("platformId") int platformId, @RequestBody PlatformRequest request) {
        return ResponseEntity.ok(platformService.update(platformId, request));
    }

    @DeleteMapping("/{platformId}")
    public ResponseEntity<Boolean> delete(@PathVariable("platformId") int platformId) {
        return ResponseEntity.ok(platformService.delete(platformId));
    }

}
