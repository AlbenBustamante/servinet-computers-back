package com.servinetcomputers.api.module.safes.domain.repository;

import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeBaseDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeBaseDto;

import java.util.List;
import java.util.Optional;

public interface SafeBaseRepository {
    SafeBaseDto save(CreateSafeBaseDto request);

    Optional<SafeBaseDto> getLastBySafeId(int safeId);

    List<SafeBaseDto> getAllBySafeDetailId(int safeDetailId);
}
