package com.servinetcomputers.api.module.platform.controller;

import com.servinetcomputers.api.module.platform.application.usecase.CreatePlatformUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.DeletePlatformUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.GetAdminPlatformDetailsUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.GetAllPlatformsUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.LoadPortalPlatformsUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.UpdatePlatformUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.AdminPlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.PortalPlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.UpdatePlatformDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
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
    public ResponseEntity<AdminPlatformDto> getDetails(@PathVariable("id") int id, @RequestParam YearMonth month) {
        return ResponseEntity.ok(getAdminPlatformDetailsUseCase.call(id, month));
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
