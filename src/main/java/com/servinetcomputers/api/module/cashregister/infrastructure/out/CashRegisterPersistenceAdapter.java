package com.servinetcomputers.api.module.cashregister.infrastructure.out;

import com.servinetcomputers.api.core.common.PersistenceAdapter;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterReadPort;
import com.servinetcomputers.api.module.cashregister.application.port.out.CashRegisterWritePort;
import com.servinetcomputers.api.module.cashregister.domain.CashRegister;
import com.servinetcomputers.api.module.cashregister.exception.CashRegisterNotFoundByIdException;
import com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.CashRegisterJpaRepository;
import com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.mapper.CashRegisterMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Adaptador de persistencia para los puertos de salida de cajas registradoras.
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class CashRegisterPersistenceAdapter implements CashRegisterReadPort, CashRegisterWritePort {
    private final CashRegisterJpaRepository repository;
    private final CashRegisterMapper mapper;

    @Override
    public CashRegister save(CashRegister cashRegister) {
        final var entity = mapper.toEntity(cashRegister);
        final var newCashRegister = repository.save(entity);

        return mapper.toDomain(newCashRegister);
    }

    @Override
    public CashRegister getById(int id) {
        final var cashRegister = repository.findByIdAndEnabledTrue(id).orElseThrow(() -> new CashRegisterNotFoundByIdException(id));
        return mapper.toDomain(cashRegister);
    }

    @Override
    public List<CashRegister> getAll() {
        final var cashRegisters = repository.findAllByEnabledTrue();
        return mapper.toDomains(cashRegisters);
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
}
