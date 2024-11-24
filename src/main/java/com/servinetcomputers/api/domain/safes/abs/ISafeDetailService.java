package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.entity.SafeBase;

import java.util.List;

public interface ISafeDetailService {

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
