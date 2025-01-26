package com.servinetcomputers.api.domain.safes.domain.repository;

import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseResponse;

public interface SafeBaseRepository {
    SafeBaseResponse save(SafeBaseRequest request);
}
