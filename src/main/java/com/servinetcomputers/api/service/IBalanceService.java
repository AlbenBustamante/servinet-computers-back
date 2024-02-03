package com.servinetcomputers.api.service;

import com.servinetcomputers.api.dto.request.BalanceRequest;
import com.servinetcomputers.api.dto.response.BalanceResponse;
import com.servinetcomputers.api.dto.response.PageResponse;

/**
 * The balance's uses case.
 */
public interface IBalanceService {

    PageResponse<BalanceResponse> register(BalanceRequest request);

    PageResponse<BalanceResponse> getAllByCampusId(int campusId);

    PageResponse<BalanceResponse> update(int balanceId, BalanceRequest request);

    boolean delete(int balanceId);

}
