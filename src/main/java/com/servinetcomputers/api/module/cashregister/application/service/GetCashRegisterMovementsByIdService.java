package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.GetCashRegisterMovementsByIdUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterDetailReadPort;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterReadPort;
import com.servinetcomputers.api.module.cashregister.domain.CashRegisterDetail;
import com.servinetcomputers.api.module.cashregister.exception.CashRegisterNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCashRegisterMovementsByIdService implements GetCashRegisterMovementsByIdUseCase {
    private final CashRegisterReadPort readPort;
    private final CashRegisterDetailReadPort detailReadPort;

    @Override
    public List<CashRegisterDetail> getMovements(Integer cashRegisterId) {
        if (!readPort.existsById(cashRegisterId)) {
            throw new CashRegisterNotFoundByIdException(cashRegisterId);
        }

        return detailReadPort.getAllByCashRegisterId(cashRegisterId);
    }
}
