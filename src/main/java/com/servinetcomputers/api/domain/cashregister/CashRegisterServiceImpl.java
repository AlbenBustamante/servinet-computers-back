package com.servinetcomputers.api.domain.cashregister;

import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterMapper;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterRepository;
import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterResponse;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.BadRequestException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.security.util.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class CashRegisterServiceImpl implements ICashRegisterService {
    private final CashRegisterRepository repository;
    private final CashRegisterMapper mapper;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public CashRegisterResponse create(CashRegisterRequest request) {
        if (repository.existsByNumeral(request.numeral())) {
            throw new BadRequestException("El numeral " + request.numeral() + " ya está siendo usado");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CashRegisterResponse> getAll(boolean enabled) {
        return mapper.toResponses(repository.findAllByEnabled(enabled));
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public CashRegisterResponse update(int id, CashRegisterRequest request) {
        final var cashRegister = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Caja registradora #" + id + " not encontrada"));

        cashRegister.setDescription(request.description() != null ? request.description() : cashRegister.getDescription());
        cashRegister.setStatus(request.status() != null ? request.status() : cashRegister.getStatus());

        return mapper.toResponse(repository.save(cashRegister));
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public CashRegisterResponse updateStatus(CashRegisterRequest request) {
        final var cashRegister = repository.findByNumeral(request.numeral())
                .orElseThrow(() -> new NotFoundException("Caja registradora con numeral " + request.numeral() + " no encontrada"));

        if (cashRegister.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Caja registradora inhabilitada");
        }

        cashRegister.setStatus(request.status());

        return mapper.toResponse(repository.save(cashRegister));
    }

    @Secured(value = ADMIN_AUTHORITY)
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
