package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.GetAllCashRegisterDetailsOfTodayUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class GetAllCashRegisterDetailsOfTodayService implements GetAllCashRegisterDetailsOfTodayUseCase {
    private final CashRegisterDetailRepository repository;

    @Transactional(readOnly = true)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public List<CashRegisterDetailResponse> call() {
        return repository.getAllOfToday();
    }
}
