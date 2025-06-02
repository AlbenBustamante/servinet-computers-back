package com.servinetcomputers.api.module.safes.controller;

import com.servinetcomputers.api.module.safes.application.usecase.CreateSafeUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.DeleteSafeUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.GetAllSafeDetailsByIdUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.GetAllSafesUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.GetSafeMovementsByIdUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/safes")
@RestController
public class SafeController {
    private final CreateSafeUseCase createSafeUseCase;
    private final GetAllSafesUseCase getAllSafesUseCase;
    private final GetAllSafeDetailsByIdUseCase getDetailsUseCase;
    private final DeleteSafeUseCase deleteSafeUseCase;
    private final GetSafeMovementsByIdUseCase getMovementsByIdUse;

    @PostMapping
    public ResponseEntity<SafeDto> register(@RequestBody CreateSafeDto request) {
        return ResponseEntity.ok(createSafeUseCase.call(request));
    }

    @GetMapping
    public ResponseEntity<List<SafeDto>> getAll() {
        return ResponseEntity.ok(getAllSafesUseCase.call());
    }

    @GetMapping(path = "/{id}/details")
    public ResponseEntity<List<SafeDetailDto>> getDetails(@PathVariable("id") int safeId) {
        return ResponseEntity.ok(getDetailsUseCase.call(safeId));
    }

    @GetMapping(path = "/{id}/movements")
    public ResponseEntity<SafeDetailDto> getMovements(@PathVariable("id") int safeId, @RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(getMovementsByIdUse.call(safeId, date));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int safeId) {
        deleteSafeUseCase.call(safeId);
        return ResponseEntity.noContent().build();
    }
}
