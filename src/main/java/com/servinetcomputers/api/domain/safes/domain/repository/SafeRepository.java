package com.servinetcomputers.api.domain.safes.domain.repository;

import com.servinetcomputers.api.domain.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeResponse;

import java.util.List;

public interface SafeRepository {
    SafeResponse create(SafeRequest request);

    List<SafeResponse> getAll();
}
