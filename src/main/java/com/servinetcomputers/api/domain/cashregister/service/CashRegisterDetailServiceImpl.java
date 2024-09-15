package com.servinetcomputers.api.domain.cashregister.service;

import com.servinetcomputers.api.domain.cashregister.abs.*;
import com.servinetcomputers.api.domain.cashregister.dto.*;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import com.servinetcomputers.api.domain.user.dto.UserResponse;
import com.servinetcomputers.api.exception.BadRequestException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashRegisterDetailServiceImpl implements ICashRegisterDetailService {
    private final CashRegisterDetailRepository repository;
    private final CashRegisterDetailMapper mapper;
    private final CashRegisterRepository cashRegisterRepository;
    private final CashRegisterMapper cashRegisterMapper;
    private final ZoneId zoneId;

    @Override
    public CashRegisterDetailResponse create(CashRegisterDetailRequest request) {
        if (repository.existsByUserIdAndCreatedDateBetweenAndEnabledTrue(request.userId(), toDateTime(LocalTime.MIN), toDateTime(LocalTime.MAX))) {
            throw new BadRequestException("Ya tienes una caja en funcionamiento");
        }

        final var cashRegister = cashRegisterRepository.findById(request.cashRegisterId())
                .orElseThrow(() -> new NotFoundException("No se encontró la caja registradora"));

        if (cashRegister.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("No se encontró la caja registradora");
        }

        if (cashRegister.getStatus().equals(CashRegisterStatus.OCCUPIED)) {
            throw new BadRequestException("La caja registradora ya está ocupada");
        }

        if (cashRegister.getStatus().equals(CashRegisterStatus.DISABLED)) {
            throw new BadRequestException("La caja registradora no está habilitada");
        }

        cashRegister.setStatus(CashRegisterStatus.OCCUPIED);

        cashRegisterRepository.save(cashRegister);

        final var entity = mapper.toEntity(request);
        entity.setCashRegister(cashRegister);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public AlreadyExistsCashRegisterDetailDto alreadyExists() {
        final var details = repository.findAllByUserIdAndCreatedDateBetweenAndEnabledTrue(userId(), toDateTime(LocalTime.MIN), toDateTime(LocalTime.MAX));
        final var alreadyExists = !details.isEmpty();
        final var myCashRegisters = alreadyExists ? mapper.toResponses(details) : null;

        final List<CashRegisterResponse> cashRegisters = alreadyExists
                ? List.of()
                : cashRegisterMapper.toResponses(cashRegisterRepository.findAllByEnabledTrue());

        return new AlreadyExistsCashRegisterDetailDto(alreadyExists, myCashRegisters, cashRegisters);
    }

    @Override
    public CashRegisterDetailResponse getById(int cashRegisterDetailId) {
        final var detail = repository.findByIdAndEnabledTrue(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento"));

        return mapper.toResponse(detail);
    }

    @Override
    public CashRegisterDetailReportsDto getReports(int cashRegisterDetailId) {
        final var entity = repository.findByIdAndEnabledTrue(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("El reporte no fue encontrado: #" + cashRegisterDetailId));

        final var cashRegisterDetail = mapper.toResponse(entity);

        final var transactionsAmount = 0;
        final var initialBase = cashRegisterDetail.getInitialBase().calculate();
        final var finalBase = cashRegisterDetail.getFinalBase().calculate();
        final var deposits = 0;
        final var withdrawals = 0;
        final var expenses = 0;
        final var credits = 0;

        final var balance = initialBase + deposits - withdrawals - expenses - credits;

        final var discrepancy = balance - finalBase;

        return new CashRegisterDetailReportsDto(
                cashRegisterDetail,
                transactionsAmount,
                initialBase,
                finalBase,
                deposits,
                withdrawals,
                expenses,
                credits,
                balance,
                discrepancy
        );
    }

    @Override
    public CashRegisterDetailResponse updateHours(int cashRegisterDetailId, CashRegisterDetailRequest req) {
        final var cashRegisterDetail = repository.findByIdAndEnabledTrue(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento"));

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

    private int userId() {
        final var auth = SecurityContextHolder.getContext().getAuthentication();

        return ((UserResponse) auth.getPrincipal()).getId();
    }

    private LocalDateTime toDateTime(LocalTime time) {
        return LocalDateTime.of(LocalDate.now(zoneId), time);
    }

}
