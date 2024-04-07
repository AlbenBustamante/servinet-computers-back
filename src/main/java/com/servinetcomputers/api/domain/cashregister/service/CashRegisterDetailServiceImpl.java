package com.servinetcomputers.api.domain.cashregister.service;

import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterDetailMapper;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterRepository;
import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterDetailService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import com.servinetcomputers.api.exception.BadRequestException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class CashRegisterDetailServiceImpl implements ICashRegisterDetailService {
    private final CashRegisterDetailRepository repository;
    private final CashRegisterDetailMapper mapper;
    private final CashRegisterRepository cashRegisterRepository;
    private final ZoneId zoneId;

    @Override
    public CashRegisterDetailResponse create(CashRegisterDetailRequest request) {
        if (repository.existsByCreatedByAndCreatedDateBetween(createdBy(), toDateTime(LocalTime.MIN), toDateTime(LocalTime.MAX))) {
            throw new BadRequestException("Ya tienes una caja en funcionamiento");
        }

        final var cashRegister = cashRegisterRepository.findById(request.cashRegisterId())
                .orElseThrow(() -> new NotFoundException("No se encontró a la caja registradora"));

        if (cashRegister.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("No se encontró a la caja registradora");
        }

        if (cashRegister.getStatus().equals(CashRegisterStatus.OCCUPIED)) {
            throw new BadRequestException("La caja registradora ya está ocupada");
        }

        if (cashRegister.getStatus().equals(CashRegisterStatus.DISABLED)) {
            throw new BadRequestException("La caja registradora no está habilitada");
        }

        cashRegister.setStatus(CashRegisterStatus.OCCUPIED);

        cashRegisterRepository.save(cashRegister);

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public boolean isAlreadyCreated() {
        return repository.existsByCreatedByAndCreatedDateBetween(createdBy(), toDateTime(LocalTime.MIN), toDateTime(LocalTime.MAX));
    }

    @Override
    public CashRegisterDetailResponse updateHours(CashRegisterDetailRequest req) {
        final var cashRegisterDetail = repository.findByCreatedByAndCreatedDateBetween(createdBy(), toDateTime(LocalTime.MIN), toDateTime(LocalTime.MAX))
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento"));

        if (cashRegisterDetail.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("No se encontró la caja en funcionamiento");
        }

        cashRegisterDetail.setWorkingHours(req.workingHours());

        return mapper.toResponse(repository.save(cashRegisterDetail));
    }

    @Override
    public boolean delete(int id) {
        final var cashRegisterDetail = repository.findById(id);

        if (cashRegisterDetail.isEmpty()) {
            return false;
        }

        cashRegisterDetail.get().setEnabled(false);

        repository.save(cashRegisterDetail.get());

        return true;
    }

    private String createdBy() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    private LocalDateTime toDateTime(LocalTime time) {
        return LocalDateTime.of(LocalDate.now(zoneId), time);
    }
}
