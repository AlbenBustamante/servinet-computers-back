package com.servinetcomputers.api.module.safes.domain.repository;

import com.servinetcomputers.api.module.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeResponse;

import java.util.List;

public interface SafeRepository {
    SafeResponse save(SafeRequest request);

    boolean existsByNumeral(int numeral);

    List<SafeResponse> getAll();
}
