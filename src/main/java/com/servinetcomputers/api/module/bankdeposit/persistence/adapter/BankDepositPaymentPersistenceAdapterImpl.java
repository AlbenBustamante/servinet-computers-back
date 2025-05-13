package com.servinetcomputers.api.module.bankdeposit.persistence.adapter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositPaymentPersistenceAdapter;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositPaymentDto;
import com.servinetcomputers.api.module.bankdeposit.persistence.JpaBankDepositPaymentRepository;
import com.servinetcomputers.api.module.bankdeposit.persistence.JpaBankDepositRepository;
import com.servinetcomputers.api.module.bankdeposit.persistence.mapper.BankDepositPaymentMapper;
import com.servinetcomputers.api.module.platform.persistence.JpaPlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class BankDepositPaymentPersistenceAdapterImpl implements BankDepositPaymentPersistenceAdapter {
    private final JpaBankDepositPaymentRepository repository;
    private final JpaBankDepositRepository bankDepositRepository;
    private final JpaPlatformRepository platformRepository;
    private final BankDepositPaymentMapper mapper;

    @Override
    public BankDepositPaymentDto save(CreateBankDepositPaymentDto dto) {
        final var bankDeposit = bankDepositRepository.findByIdAndEnabledTrue(dto.bankDepositId())
                .orElseThrow(() -> new NotFoundException("No se encontró el depósito bancario: " + dto.bankDepositId()));

        final var platform = platformRepository.findByIdAndEnabledTrue(dto.platformId())
                .orElseThrow(() -> new NotFoundException("No se encontró la plataforma: " + dto.platformId()));

        final var entity = mapper.toEntity(dto);
        entity.setBankDeposit(bankDeposit);
        entity.setPlatform(platform);

        final var newPayment = repository.save(entity);

        return mapper.toDto(newPayment);
    }

    @Override
    public Integer getAmountByPlatformIdBetween(Integer platformId, LocalDateTime startDate, LocalDateTime endDate) {
        final var amount = repository.countByPlatformIdAndEnabledTrueAndCreatedDateBetween(platformId, startDate, endDate);
        return amount != null ? amount : 0;
    }

    @Override
    public Integer getTotalByPlatformIdBetween(Integer platformId, LocalDateTime startDate, LocalDateTime endDate) {
        final var total = repository.sumAllByPlatformIdAndEnabledTrueAndCreatedDateBetween(platformId, startDate, endDate);
        return total != null ? total : 0;
    }
}
