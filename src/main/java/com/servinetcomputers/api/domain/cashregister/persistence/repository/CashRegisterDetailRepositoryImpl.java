package com.servinetcomputers.api.domain.cashregister.persistence.repository;

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
import com.servinetcomputers.api.domain.transaction.persistence.JpaTransactionDetailRepository;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
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
    private final JpaTransactionDetailRepository jpaTransactionDetailRepository;
    private final CashRegisterDetailMapper mapper;
    private final CashRegisterMapper cashRegisterMapper;
    private final BaseMapper baseMapper;
    private final ZoneId zoneId;

    @Override
    public boolean existsById(int id) {
        return repository.existsByIdAndEnabledTrue(id);
    }

    @Override
    public boolean existsByUserIdAndStatusNot(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterStatus status) {
        return repository.existsByUserIdAndCreatedDateBetweenAndEnabledTrueAndCashRegisterStatusNot(userId, startDate, endDate, status);
    }

    @Override
    public List<CashRegisterDetailResponse> getAllByUserId(int userId, LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByUserIdAndCreatedDateBetweenAndEnabledTrue(userId, startDate, endDate);
        return mapper.toResponses(details);
    }

    @Override
    public void save(CashRegisterDetailRequest request) {
        final var entity = mapper.toEntity(request);
        repository.save(entity);
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
