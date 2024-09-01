package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceResponse;

/**
 * The balance's uses case.
 */
public interface IPlatformBalanceService {

    PlatformBalanceResponse update(int balanceId, PlatformBalanceRequest request);

    boolean delete(int balanceId);

}
