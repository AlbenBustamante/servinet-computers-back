package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.GetAllCashRegistersUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterReadPort;
import com.servinetcomputers.api.module.cashregister.domain.CashRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllCashRegistersService implements GetAllCashRegistersUseCase {
    private final CashRegisterReadPort readPort;

    @Override
    public List<CashRegister> getAll() {
        return readPort.getAll();
    }
}
