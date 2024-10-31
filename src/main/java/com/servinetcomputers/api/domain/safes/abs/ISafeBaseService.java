package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeBaseResponse;

public interface ISafeBaseService {
    SafeBaseResponse create(int safeId, SafeBaseRequest request);
}
