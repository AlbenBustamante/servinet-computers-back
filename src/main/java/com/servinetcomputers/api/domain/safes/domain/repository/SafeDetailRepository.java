package com.servinetcomputers.api.domain.safes.domain.repository;

import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;

import java.util.List;
import java.util.Optional;

public interface SafeDetailRepository {
    SafeDetailResponse save(SafeDetailResponse response);

    Optional<SafeDetailResponse> get(int id);

    List<SafeDetailResponse> loadSafes();
}
