package com.servinetcomputers.api.module.cashregister.persistence.repository;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterRequest;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterResponse;
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
    public CashRegisterResponse save(CashRegisterRequest request) {
        final var entity = mapper.toEntity(request);
        final var newCashRegister = repository.save(entity);

        return mapper.toResponse(newCashRegister);
    }

    @Override
    public CashRegisterResponse save(CashRegisterResponse response) {
        final var entity = mapper.toEntity(response);
        final var newCashRegister = repository.save(entity);

        return mapper.toResponse(newCashRegister);
    }

    @Override
    public Optional<CashRegisterResponse> get(int id) {
        final var cashRegister = repository.findByIdAndEnabledTrue(id);
        return cashRegister.map(mapper::toResponse);
    }

    @Override
    public List<CashRegisterResponse> getAll() {
        final var cashRegisters = repository.findAllByEnabledTrue();
        return mapper.toResponses(cashRegisters);
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
