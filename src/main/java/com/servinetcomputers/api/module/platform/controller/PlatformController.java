package com.servinetcomputers.api.module.platform.controller;

import com.servinetcomputers.api.module.platform.application.usecase.*;
import com.servinetcomputers.api.module.platform.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    private final GetAdminPlatformDetailsUseCase getAdminPlatformDetailsUseCase;
    private final UpdatePlatformUseCase updatePlatformUseCase;
    private final DeletePlatformUseCase deletePlatformUseCase;

    @PostMapping
    public ResponseEntity<PlatformDto> register(@RequestBody CreatePlatformDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createPlatformUseCase.call(request));
    }

    @GetMapping
    public ResponseEntity<List<PlatformDto>> getAll() {
        return ResponseEntity.ok(getAllPlatformsUseCase.call());
    }

    @GetMapping(path = "/portal")
    public ResponseEntity<List<PortalPlatformDto>> loadPortalPlatforms() {
        return ResponseEntity.ok(loadPortalPlatformsUseCase.call());
    }

    @GetMapping(path = "/{id}/details")
    public ResponseEntity<AdminPlatformDto> getDetails(@PathVariable("id") int id, @RequestParam LocalDate date) {
        return ResponseEntity.ok(getAdminPlatformDetailsUseCase.call(id, date));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<PlatformDto> update(@PathVariable("id") int id, @RequestBody UpdatePlatformDto updatePlatformDto) {
        return ResponseEntity.ok(updatePlatformUseCase.call(id, updatePlatformDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int platformId) {
        deletePlatformUseCase.call(platformId);
        return ResponseEntity.ok().build();
    }
}
