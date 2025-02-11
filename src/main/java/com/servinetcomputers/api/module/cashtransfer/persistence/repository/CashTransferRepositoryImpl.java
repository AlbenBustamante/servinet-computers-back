package com.servinetcomputers.api.module.cashtransfer.persistence.repository;

import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import com.servinetcomputers.api.module.cashtransfer.persistence.JpaCashTransferRepository;
import com.servinetcomputers.api.module.cashtransfer.persistence.mapper.CashTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CashTransferRepositoryImpl implements CashTransferRepository {
    private final JpaCashTransferRepository repository;
    private final CashTransferMapper mapper;

    @Override
    public CashTransferDto save(CreateCashTransferDto createCashTransferDto) {
        final var entity = mapper.toEntity(createCashTransferDto);
        final var newCashTransfer = repository.save(entity);

        return mapper.toDto(newCashTransfer);
    }

    @Override
    public List<CashTransferDto> getAllByUserId(int userId, LocalDateTime startDate, LocalDateTime endDate) {
        final var transfers = repository.findAllByFromCashRegisterIdOrToCashRegisterIdOrFromSafeIdOrToSafeIdAndEnabledTrueAndCreatedDateBetween(userId, userId, userId, userId, startDate, endDate);
        return mapper.toDto(transfers);
    }
}
