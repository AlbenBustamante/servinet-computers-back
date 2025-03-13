package com.servinetcomputers.api.module.safes.domain.repository;

import com.servinetcomputers.api.module.safes.domain.dto.SafeBaseRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeBaseResponse;

import java.util.List;
import java.util.Optional;

public interface SafeBaseRepository {
    SafeBaseResponse save(SafeBaseRequest request);

    Optional<SafeBaseResponse> getLastBySafeId(int safeId);

    List<SafeBaseResponse> getAllBySafeDetailId(int safeDetailId);
}
