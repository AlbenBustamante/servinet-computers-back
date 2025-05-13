package com.servinetcomputers.api.module.safes.domain.repository;

import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDto;

import java.util.List;
import java.util.Optional;

public interface SafeRepository {
    SafeDto save(CreateSafeDto request);

    SafeDto save(SafeDto response);

    boolean existsByNumeral(int numeral);

    Optional<SafeDto> get(int safeId);

    List<SafeDto> getAll();
}
