package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.base.Base;
import com.servinetcomputers.api.module.base.BaseMapper;
import com.servinetcomputers.api.module.cashregister.application.port.in.GetLastBaseUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterDetailReadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetLastBaseService implements GetLastBaseUseCase {
    private final CashRegisterDetailReadPort readPort;
    private final BaseMapper baseMapper;

    @Override
    public Base getLastBase(Integer id) {
        final var base = readPort.getLastFinalBase(id);

        if (base == null || base.isEmpty()) {
            return Base.zero();
        }

        return baseMapper.toBase(base);
    }
}
