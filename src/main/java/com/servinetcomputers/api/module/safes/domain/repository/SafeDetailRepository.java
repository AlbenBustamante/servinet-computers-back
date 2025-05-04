package com.servinetcomputers.api.module.safes.domain.repository;

import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SafeDetailRepository {
    SafeDetailDto save(CreateSafeDetailDto request);

    SafeDetailDto save(SafeDetailDto response);

    Optional<SafeDetailDto> get(int id);

    List<SafeDetailDto> getAllBySafeId(int safeId);

    List<SafeDetailDto> getAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<Integer> getNumeralById(int id);
}
