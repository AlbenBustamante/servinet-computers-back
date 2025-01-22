package com.servinetcomputers.api.domain.platform.domain.repository;

import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformBalanceResponse;

/**
 * The balance's repository.
 */
public interface PlatformBalanceRepository {
    PlatformBalanceResponse update(int balanceId, PlatformBalanceRequest request);

    boolean delete(int balanceId);
}
