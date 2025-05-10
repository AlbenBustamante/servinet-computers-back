package com.servinetcomputers.api.module.bankdeposit.persistence.repository;

import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.repository.BankDepositRepository;
import com.servinetcomputers.api.module.bankdeposit.persistence.JpaBankDepositRepository;
import com.servinetcomputers.api.module.bankdeposit.persistence.mapper.BankDepositMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BankDepositRepositoryImpl implements BankDepositRepository {
    private final JpaBankDepositRepository repository;
    private final BankDepositMapper mapper;

    @Override
    public BankDepositDto save(CreateBankDepositDto dto) {
        final var entity = mapper.toEntity(dto);
        final var bankDeposit = repository.save(entity);

        return mapper.toDto(bankDeposit);
    }

    @Override
    public Optional<BankDepositDto> get(Integer bankDepositId) {
        final var entity = repository.findByIdAndEnabledTrue(bankDepositId);
        return entity.map(mapper::toDto);
    }

    @Override
    public List<BankDepositDto> getAllBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var bankDeposits = repository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return mapper.toDto(bankDeposits);
    }
}
