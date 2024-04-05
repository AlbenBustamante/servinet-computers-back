package com.servinetcomputers.api.domain.cashregister;

import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterMapper;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterRepository;
import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterResponse;
import com.servinetcomputers.api.exception.BadRequestException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CashRegisterServiceImpl implements ICashRegisterService {
    private final CashRegisterRepository repository;
    private final CashRegisterMapper mapper;

    @Override
    public CashRegisterResponse create(CashRegisterRequest request) {
        if (repository.existsByNumeral(request.numeral())) {
            throw new BadRequestException("El numeral " + request.numeral() + " ya está siendo usado");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public List<CashRegisterResponse> getAll() {
        return mapper.toResponses(repository.findAll());
    }

    @Override
    public CashRegisterResponse updateStatus(CashRegisterRequest request) {
        final var cashRegister = repository.findByNumeral(request.numeral())
                .orElseThrow(() -> new NotFoundException("Caja registradora con numeral " + request.numeral() + " no encontrada."));

        cashRegister.setStatus(request.status());

        return mapper.toResponse(repository.save(cashRegister));
    }

    @Override
    public boolean delete(int id) {
        final var cashRegister = repository.findById(id);

        if (cashRegister.isEmpty()) {
            return false;
        }

        cashRegister.get().setEnabled(false);

        repository.save(cashRegister.get());

        return true;
    }

}
