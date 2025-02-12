package com.servinetcomputers.api.module.safes.domain.repository;

import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SafeDetailRepository {
    SafeDetailResponse save(SafeDetailRequest request);

    SafeDetailResponse save(SafeDetailResponse response);

    Optional<SafeDetailResponse> get(int id);

    List<SafeDetailResponse> getAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<Integer> getNumeralById(int id);
}
