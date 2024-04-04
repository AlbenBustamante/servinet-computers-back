package com.servinetcomputers.api.domain.balance.abs;

import com.servinetcomputers.api.domain.PageResponse;
import com.servinetcomputers.api.domain.balance.dto.BalanceRequest;
import com.servinetcomputers.api.domain.balance.dto.BalanceResponse;

/**
 * The balance's uses case.
 */
public interface IBalanceService {

    PageResponse<BalanceResponse> register(BalanceRequest request);

    PageResponse<BalanceResponse> update(int balanceId, BalanceRequest request);

    boolean delete(int balanceId);

}
