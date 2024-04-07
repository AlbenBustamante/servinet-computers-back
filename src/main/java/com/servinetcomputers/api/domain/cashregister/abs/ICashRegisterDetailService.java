package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailReq;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailRes;

import java.time.LocalDate;

public interface ICashRegisterDetailService {

    CashRegisterDetailRes create(CashRegisterDetailReq request);

    CashRegisterDetailRes getByCreatedByAndCreatedDate(String createdBy, LocalDate date);

    CashRegisterDetailRes updateHours(String workingHours);

    boolean delete(int id);

}
