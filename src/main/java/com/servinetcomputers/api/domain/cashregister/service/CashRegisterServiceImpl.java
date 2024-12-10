package com.servinetcomputers.api.domain.cashregister.service;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterMapper;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterRepository;
import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterResponse;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class CashRegisterServiceImpl implements ICashRegisterService {
    private final CashRegisterRepository repository;
    private final CashRegisterMapper mapper;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;
    private final BaseMapper baseMapper;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public CashRegisterResponse create(CashRegisterRequest request) {
        if (repository.existsByNumeralAndEnabledTrue(request.numeral())) {
            throw new BadRequestException("El numeral " + request.numeral() + " ya está siendo usado");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CashRegisterResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrue());
    }

    @Transactional(readOnly = true)
    @Override
    public BaseDto getLastFinalBaseFromCashRegisterId(int cashRegisterId) {
        final var cashRegisterDetailPage = cashRegisterDetailRepository.findBaseByCashRegisterId(cashRegisterId, PageRequest.of(0, 1));

        if (cashRegisterDetailPage.isEmpty()) {
            return null;
        }

        final var finalBase = cashRegisterDetailPage.getContent().get(0);

        return baseMapper.toDto(finalBase);
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public CashRegisterResponse update(int id, CashRegisterRequest request) {
        final var cashRegister = repository.findByIdAndEnabledTrue(id)
                .orElseThrow(() -> new NotFoundException("Caja registradora #" + id + " no encontrada"));

        cashRegister.setDescription(request.description() != null ? request.description() : cashRegister.getDescription());
        cashRegister.setStatus(request.status() != null ? request.status() : cashRegister.getStatus());

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
