package com.servinetcomputers.api.module.cashregister.application.service;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.cashregister.application.usecase.GetAllMovementsUseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllMovementsService implements GetAllMovementsUseCase {
    private final CashRegisterRepository repository;
    private final CashRegisterDetailPersistenceAdapter cashRegisterDetailPersistenceAdapter;

    /**
     * Get all details of a cash register by its ID.
     *
     * @param cashRegisterId the cash register ID to search.
     * @return the cash register details found.
     */
    @Transactional(readOnly = true)
    @Override
    public List<CashRegisterDetailDto> call(Integer cashRegisterId, LocalDate date) {
        if (!repository.existsById(cashRegisterId)) {
            throw new NotFoundException("No se encontró la caja con ID: #" + cashRegisterId);
        }

        final var startDate = date.atStartOfDay();
        final var endDate = date.plusDays(1).atStartOfDay();

        return cashRegisterDetailPersistenceAdapter.getAllByCashRegisterIdBetween(cashRegisterId, startDate, endDate);
    }
}
