package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.base.BaseMapper;
import com.servinetcomputers.api.module.cashregister.application.usecase.GetLastBaseUseCase;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetLastBaseService implements GetLastBaseUseCase {
    private final CashRegisterRepository repository;
    private final BaseMapper baseMapper;

    @Transactional(readOnly = true)
    @Override
    public BaseDto call(Integer param) {
        final var cashRegisterDetailPage = repository.getLastFinalBase(param);

        if (cashRegisterDetailPage.isEmpty()) {
            return BaseDto.zero();
        }

        final var finalBase = cashRegisterDetailPage.getContent().get(0);

        return baseMapper.toDto(finalBase);
    }
}
