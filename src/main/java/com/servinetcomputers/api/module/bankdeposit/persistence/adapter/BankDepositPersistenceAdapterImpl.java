package com.servinetcomputers.api.module.bankdeposit.persistence.adapter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositPersistenceAdapter;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateDepositorDto;
import com.servinetcomputers.api.module.bankdeposit.persistence.JpaBankDepositRepository;
import com.servinetcomputers.api.module.bankdeposit.persistence.mapper.BankDepositMapper;
import com.servinetcomputers.api.module.bankdeposit.persistence.mapper.DepositorMapper;
import com.servinetcomputers.api.module.cashregister.persistence.JpaCashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BankDepositPersistenceAdapterImpl implements BankDepositPersistenceAdapter {
    private final JpaBankDepositRepository jpaBankDepositRepository;
    private final JpaCashRegisterDetailRepository jpaCashRegisterDetailRepository;
    private final BankDepositMapper bankDepositMapper;
    private final DepositorMapper depositorMapper;

    @Override
    public BankDepositDto enrollDepositor(CreateDepositorDto createDepositorDto) {
        final var id = createDepositorDto.getPk();

        final var bankDeposit = jpaBankDepositRepository.findByIdAndEnabledTrue(id.bankDepositId())
                .orElseThrow(() -> new NotFoundException("No se encontró el depósito : " + id.bankDepositId()));

        final var cashRegisterDetail = jpaCashRegisterDetailRepository.findByIdAndEnabledTrue(id.cashRegisterDetailId())
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada: " + id.cashRegisterDetailId()));

        final var depositor = depositorMapper.toEntity(createDepositorDto);
        depositor.setBankDeposit(bankDeposit);
        depositor.setCashRegisterDetail(cashRegisterDetail);

        bankDeposit.addDepositor(depositor);
        final var updatedBankDeposit = jpaBankDepositRepository.save(bankDeposit);

        return bankDepositMapper.toDto(updatedBankDeposit);
    }

    @Override
    public BankDepositDto setStatus(Integer bankDepositDto, BankDepositStatus status) {
        var bankDeposit = jpaBankDepositRepository.findByIdAndEnabledTrue(bankDepositDto)
                .orElseThrow(() -> new NotFoundException("No se encontró el depósito bancario: " + bankDepositDto));

        bankDeposit.setStatus(status);
        return bankDepositMapper.toDto(bankDeposit);
    }

    @Override
    public BankDepositDto get(Integer bankDepositId) {
        final var entity = jpaBankDepositRepository.findByIdAndEnabledTrue(bankDepositId)
                .orElseThrow(() -> new NotFoundException("No se encontró el depósito bancario: " + bankDepositId));

        return bankDepositMapper.toDto(entity);
    }
}
