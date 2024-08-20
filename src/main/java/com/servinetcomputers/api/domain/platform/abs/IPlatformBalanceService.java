package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceResponse;

import java.util.List;

/**
 * The balance's uses case.
 */
public interface IPlatformBalanceService {

    List<PlatformBalanceResponse> createInitialBalances();

    boolean existsCurrentDateBalances();

    PlatformBalanceResponse update(int balanceId, PlatformBalanceRequest request);

    boolean delete(int balanceId);

}
