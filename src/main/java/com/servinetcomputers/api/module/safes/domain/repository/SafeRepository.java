package com.servinetcomputers.api.module.safes.domain.repository;

import com.servinetcomputers.api.module.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeResponse;

import java.util.List;
import java.util.Optional;

public interface SafeRepository {
    SafeResponse save(SafeRequest request);

    SafeResponse save(SafeResponse response);

    boolean existsByNumeral(int numeral);

    Optional<SafeResponse> get(int safeId);

    List<SafeResponse> getAll();
}
