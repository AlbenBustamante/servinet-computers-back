package com.servinetcomputers.api.domain.safes.domain.repository;

import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.persistence.entity.SafeBase;

import java.util.List;

public interface SafeDetailRepository {
    List<SafeDetailResponse> loadSafes();

    /**
     * If the initial base is by default, then update the initial and final base.
     * <p>If not, then update only the final base.</p>
     * <p>Also, create a new {@link SafeBase} record.</p>
     *
     * @param request a {@link SafeBaseRequest} model dto.
     * @return a {@link SafeDetailResponse} model dto.
     */
    SafeDetailResponse updateBase(SafeBaseRequest request);
}
