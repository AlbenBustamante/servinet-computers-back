package com.servinetcomputers.api.module.cashregister.persistence.repository;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterRepository;
import com.servinetcomputers.api.module.cashregister.persistence.JpaCashRegisterDetailRepository;
import com.servinetcomputers.api.module.cashregister.persistence.JpaCashRegisterRepository;
import com.servinetcomputers.api.module.cashregister.persistence.mapper.CashRegisterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CashRegisterRepositoryImpl implements CashRegisterRepository {
    private final JpaCashRegisterRepository repository;
    private final JpaCashRegisterDetailRepository jpaCashRegisterDetailRepository;
    private final CashRegisterMapper mapper;

    @Override
    public CashRegisterDto save(CreateCashRegisterDto request) {
        final var entity = mapper.toEntity(request);
        final var newCashRegister = repository.save(entity);

        return mapper.toDto(newCashRegister);
    }

    @Override
    public CashRegisterDto save(CashRegisterDto response) {
        final var entity = mapper.toEntity(response);
        final var newCashRegister = repository.save(entity);

        return mapper.toDto(newCashRegister);
    }

    @Override
    public Optional<CashRegisterDto> get(int id) {
        final var cashRegister = repository.findByIdAndEnabledTrue(id);
        return cashRegister.map(mapper::toDto);
    }

    @Override
    public List<CashRegisterDto> getAll() {
        final var cashRegisters = repository.findAllByEnabledTrue();
        return mapper.toDto(cashRegisters);
    }

    @Override
    public List<Integer> getAllIds() {
        return repository.findAllIdsAndEnabledTrue();
    }

    @Override
    public boolean existsById(int id) {
        return repository.existsByIdAndEnabledTrue(id);
    }

    @Override
    public boolean existsByNumeral(int numeral) {
        return repository.existsByNumeralAndEnabledTrue(numeral);
    }

    @Override
    public Page<String> getLastFinalBase(int cashRegisterId) {
        return jpaCashRegisterDetailRepository.findBaseByCashRegisterId(cashRegisterId, PageRequest.of(0, 1));
    }
}
