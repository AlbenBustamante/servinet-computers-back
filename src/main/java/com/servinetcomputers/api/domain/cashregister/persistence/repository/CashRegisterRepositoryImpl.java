package com.servinetcomputers.api.domain.cashregister.persistence.repository;

import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterResponse;
import com.servinetcomputers.api.domain.cashregister.domain.dto.UpdateCashRegisterDto;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.mapper.CashRegisterMapper;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CashRegisterRepositoryImpl implements CashRegisterRepository {
    private final JpaCashRegisterRepository repository;
    private final JpaCashRegisterDetailRepository jpaCashRegisterDetailRepository;
    private final BaseMapper baseMapper;
    private final CashRegisterMapper mapper;

    @Override
    public Optional<CashRegisterResponse> get(int id) {
        return repository.findByIdAndEnabledTrue(id).map(mapper::toResponse);
    }

    @Override
    public CashRegisterResponse save(CashRegisterResponse response) {
        final var entity = mapper.toEntity(response);
        final var newCashRegister = repository.save(entity);

        return mapper.toResponse(newCashRegister);
    }

    @Override
    public CashRegisterResponse create(CashRegisterRequest request) {
        if (repository.existsByNumeralAndEnabledTrue(request.getNumeral())) {
            throw new BadRequestException("El numeral " + request.getNumeral() + " ya está siendo usado");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public List<CashRegisterResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrue());
    }

    @Override
    public BaseDto getLastFinalBaseFromCashRegisterId(int cashRegisterId) {
        final var cashRegisterDetailPage = jpaCashRegisterDetailRepository.findBaseByCashRegisterId(cashRegisterId, PageRequest.of(0, 1));

        if (cashRegisterDetailPage.isEmpty()) {
            return null;
        }

        final var finalBase = cashRegisterDetailPage.getContent().get(0);

        return baseMapper.toDto(finalBase);
    }

    @Override
    public CashRegisterResponse update(UpdateCashRegisterDto updateCashRegisterDto) {
        final var id = updateCashRegisterDto.getId();

        final var cashRegister = repository.findByIdAndEnabledTrue(id)
                .orElseThrow(() -> new NotFoundException("Caja registradora #" + id + " no encontrada"));

        cashRegister.setDescription(updateCashRegisterDto.getDescription() != null ? updateCashRegisterDto.getDescription() : cashRegister.getDescription());
        cashRegister.setStatus(updateCashRegisterDto.getStatus() != null ? updateCashRegisterDto.getStatus() : cashRegister.getStatus());

        return mapper.toResponse(repository.save(cashRegister));
    }

    @Override
    public boolean delete(int id) {
        final var cashRegister = repository.findById(id);

        if (cashRegister.isEmpty()) {
            return false;
        }

        cashRegister.get().setStatus(CashRegisterStatus.DISABLED);
        cashRegister.get().setEnabled(false);

        repository.save(cashRegister.get());

        return true;
    }
}
