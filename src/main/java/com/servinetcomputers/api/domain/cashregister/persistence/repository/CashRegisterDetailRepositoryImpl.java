package com.servinetcomputers.api.domain.cashregister.persistence.repository;

import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.domain.dto.*;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.domain.cashregister.persistence.mapper.CashRegisterMapper;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailStatus;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import com.servinetcomputers.api.domain.expense.persistence.JpaExpenseRepository;
import com.servinetcomputers.api.domain.transaction.persistence.JpaTransactionDetailRepository;
import com.servinetcomputers.api.domain.transaction.util.TransactionDetailType;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import com.servinetcomputers.api.domain.user.persistence.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CashRegisterDetailRepositoryImpl implements CashRegisterDetailRepository {
    private final JpaCashRegisterDetailRepository repository;
    private final JpaCashRegisterRepository jpaCashRegisterRepository;
    private final JpaExpenseRepository jpaExpenseRepository;
    private final JpaTransactionDetailRepository jpaTransactionDetailRepository;
    private final JpaUserRepository jpaUserRepository;
    private final CashRegisterDetailMapper mapper;
    private final CashRegisterMapper cashRegisterMapper;
    private final BaseMapper baseMapper;
    private final ZoneId zoneId;

    @Override
    public boolean existsById(int id) {
        return repository.existsByIdAndEnabledTrue(id);
    }

    @Override
    public MyCashRegistersReports create(CashRegisterDetailRequest request) {
        if (repository.existsByUserIdAndCreatedDateBetweenAndEnabledTrueAndCashRegisterStatusNot(request.userId(), toDateTime(LocalTime.MIN), toDateTime(LocalTime.MAX), CashRegisterStatus.AVAILABLE)) {
            throw new BadRequestException("Ya tienes una caja en funcionamiento");
        }

        final var cashRegister = jpaCashRegisterRepository.findById(request.cashRegisterId())
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
        jpaCashRegisterRepository.save(cashRegister);

        final var entity = mapper.toEntity(request);
        entity.setCashRegister(cashRegister);

        final var user = jpaUserRepository.findByIdAndEnabledTrue(request.userId())
                .orElseThrow(() -> new NotFoundException("Usuario #" + request.userId() + " no encontrado"));

        entity.setUser(user);
        repository.save(entity);

        return getReportsByUserId(request.userId());
    }

    @Override
    public List<CashRegisterDetailResponse> getAllOfToday() {
        final var today = LocalDate.now();
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now());

        return mapper.toResponses(repository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate));
    }

    @Override
    public AlreadyExistsCashRegisterDetailDto alreadyExists() {
        final var details = repository.findAllByUserIdAndCreatedDateBetweenAndEnabledTrueAndCashRegisterStatusNot(userId(), toDateTime(LocalTime.MIN), toDateTime(LocalTime.MAX), CashRegisterStatus.AVAILABLE);
        final var alreadyExists = !details.isEmpty();

        final var myCashRegisters = alreadyExists ? getReportsByUserId(userId()) : null;

        final List<CashRegisterResponse> cashRegisters = alreadyExists
                ? List.of()
                : cashRegisterMapper.toResponses(jpaCashRegisterRepository.findAllByEnabledTrue());

        return new AlreadyExistsCashRegisterDetailDto(alreadyExists, myCashRegisters, cashRegisters);
    }

    @Override
    public CashRegisterDetailResponse getById(int cashRegisterDetailId) {
        final var detail = repository.findByIdAndEnabledTrue(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento"));

        return mapper.toResponse(detail);
    }

    @Override
    public MyCashRegistersReports getReportsByUserId(int userId) {
        final var details = repository.findAllByUserIdAndCreatedDateBetweenAndEnabledTrue(userId, toDateTime(LocalTime.MIN), toDateTime(LocalTime.now()));
        final var cashRegisterDetails = mapper.toResponses(details);
        final List<CashRegisterDetailReportsDto> reports = new ArrayList<>(details.size());

        var total = new CashRegisterDetailReportsDto(cashRegisterDetails.get(0), 0, 0, 0, 0, 0, 0, 0, 0, 0);

        for (final var cashRegisterDetail : cashRegisterDetails) {
            final var report = getCashRegistersReports(cashRegisterDetail);

            reports.add(report);
            total = total.sum(report);
        }

        return new MyCashRegistersReports(reports, total);
    }

    @Override
    public CashRegisterDetailReportsDto getCashRegisterDetailReports(int cashRegisterDetailId) {
        final var cashRegisterDetail = repository.findByIdAndEnabledTrue(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada #" + cashRegisterDetailId));

        return getCashRegistersReports(mapper.toResponse(cashRegisterDetail));
    }

    private CashRegisterDetailReportsDto getCashRegistersReports(CashRegisterDetailResponse cashRegisterDetail) {
        final var startDate = cashRegisterDetail.getCreatedDate();
        final var endDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        final var code = cashRegisterDetail.getCreatedBy();

        final var finalBaseDto = cashRegisterDetail.getDetailFinalBase();

        final var transactionsAmount = 0;
        final var initialBase = cashRegisterDetail.getDetailInitialBase().calculate();
        final var finalBase = finalBaseDto != null ? finalBaseDto.calculate() : 0;

        var deposits = jpaTransactionDetailRepository.sumAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate, TransactionDetailType.DEPOSIT);
        var withdrawals = jpaTransactionDetailRepository.sumAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate, TransactionDetailType.WITHDRAWAL);

        deposits = deposits != null ? deposits : 0;
        withdrawals = withdrawals != null ? withdrawals : 0;

        var expenses = jpaExpenseRepository.sumAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, false);
        var discounts = jpaExpenseRepository.sumAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, true);

        expenses = expenses != null ? expenses : 0;
        discounts = discounts != null ? discounts : 0;

        withdrawals += expenses + discounts;

        final var balance = initialBase + deposits - withdrawals - expenses - discounts;

        final var discrepancy = finalBase - balance;

        return new CashRegisterDetailReportsDto(
                cashRegisterDetail,
                transactionsAmount,
                initialBase,
                finalBase,
                deposits,
                withdrawals,
                expenses,
                discounts,
                balance,
                discrepancy
        );
    }

    @Override
    public CashRegisterDetailResponse startBreak(int cashRegisterDetailId) {
        return handleBreak(cashRegisterDetailId, true);
    }

    @Override
    public CashRegisterDetailResponse endBreak(int cashRegisterDetailId) {
        return handleBreak(cashRegisterDetailId, false);
    }

    @Override
    public CashRegisterDetailReportsDto close(int cashRegisterDetailId, BaseDto finalBase) {
        final var cashRegisterDetail = repository.findByIdAndEnabledTrue(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento"));

        cashRegisterDetail.getCashRegister().setStatus(CashRegisterStatus.AVAILABLE);
        cashRegisterDetail.setStatus(CashRegisterDetailStatus.CLOSED);

        final var workingHours = cashRegisterDetail.getWorkingHours();
        workingHours[3] = LocalTime.now();

        cashRegisterDetail.setWorkingHours(workingHours);
        cashRegisterDetail.setFinalBase(baseMapper.toStr(finalBase));

        final var response = mapper.toResponse(repository.save(cashRegisterDetail));

        return getCashRegistersReports(response);
    }

    private CashRegisterDetailResponse handleBreak(int cashRegisterDetailId, boolean startBreak) {
        final var cashRegisterDetail = repository.findByIdAndEnabledTrue(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la caja en funcionamiento"));

        final var workingHours = cashRegisterDetail.getWorkingHours();
        workingHours[startBreak ? 1 : 2] = LocalTime.now();

        cashRegisterDetail.setWorkingHours(workingHours);
        cashRegisterDetail.setStatus(startBreak ? CashRegisterDetailStatus.RESTING : CashRegisterDetailStatus.WORKING);

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
