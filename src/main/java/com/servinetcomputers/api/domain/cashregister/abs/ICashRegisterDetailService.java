package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailReq;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailRes;

public interface ICashRegisterDetailService {

    CashRegisterDetailRes create(CashRegisterDetailReq request);

    boolean isAlreadyCreated();

    CashRegisterDetailRes updateHours(CashRegisterDetailReq req);

    boolean delete(int id);

}
