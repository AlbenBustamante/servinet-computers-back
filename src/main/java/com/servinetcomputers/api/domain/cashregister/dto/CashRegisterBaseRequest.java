package com.servinetcomputers.api.domain.cashregister.dto;

import com.servinetcomputers.api.domain.base.BaseDto;

public record CashRegisterBaseRequest(int cashRegisterDetailId, BaseDto initialBase, BaseDto finalBase,
                                      String observation) {
}
