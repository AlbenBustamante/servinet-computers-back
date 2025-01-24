package com.servinetcomputers.api.domain.platform.controller;

import com.servinetcomputers.api.domain.platform.application.usecase.*;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.PortalPlatformDto;
import com.servinetcomputers.api.domain.platform.domain.dto.UpdatePlatformDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The platform's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping(path = "/platforms")
@RestController
public class PlatformController {
    private final CreatePlatformUseCase createPlatformUseCase;
    private final GetAllPlatformsUseCase getAllPlatformsUseCase;
    private final LoadPortalPlatformsUseCase loadPortalPlatformsUseCase;
    private final UpdatePlatformUseCase updatePlatformUseCase;
    private final DeletePlatformUseCase deletePlatformUseCase;

    @PostMapping
    public ResponseEntity<PlatformResponse> register(@RequestBody PlatformRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPlatformUseCase.call(request));
    }

    @GetMapping
    public ResponseEntity<List<PlatformResponse>> getAll() {
        return ResponseEntity.ok(getAllPlatformsUseCase.call());
    }

    @GetMapping(path = "/portal")
    public ResponseEntity<List<PortalPlatformDto>> loadPortalPlatforms() {
        return ResponseEntity.ok(loadPortalPlatformsUseCase.call());
    }

    @PatchMapping
    public ResponseEntity<PlatformResponse> update(@RequestBody UpdatePlatformDto updatePlatformDto) {
        return ResponseEntity.ok(updatePlatformUseCase.call(updatePlatformDto));
    }

    @DeleteMapping(path = "/{platformId}")
    public ResponseEntity<Boolean> delete(@PathVariable("platformId") int platformId) {
        deletePlatformUseCase.call(platformId);
        return ResponseEntity.ok().build();
    }
}
