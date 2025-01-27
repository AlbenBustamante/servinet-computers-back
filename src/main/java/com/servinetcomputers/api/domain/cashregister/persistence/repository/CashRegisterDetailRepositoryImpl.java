package com.servinetcomputers.api.domain.cashregister.persistence.repository;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailRequest;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterDetailStatus;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CashRegisterDetailRepositoryImpl implements CashRegisterDetailRepository {
    private final JpaCashRegisterDetailRepository repository;
    private final CashRegisterDetailMapper mapper;
    private final BaseMapper baseMapper;

    @Override
    public void save(CashRegisterDetailRequest request) {
        final var entity = mapper.toEntity(request);
        repository.save(entity);
    }

    @Override
    public CashRegisterDetailResponse save(CashRegisterDetailResponse response) {
        final var entity = mapper.toEntity(response);
        final var newDetail = repository.save(entity);

        return mapper.toResponse(newDetail);
    }

    @Override
    public boolean existsByUserIdAndStatusNot(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterStatus status) {
        return repository.existsByUserIdAndCreatedDateBetweenAndEnabledTrueAndCashRegisterStatusNot(userId, startDate, endDate, status);
    }

    @Override
    public boolean existsById(int id) {
        return repository.existsByIdAndEnabledTrue(id);
    }

    @Override
    public List<CashRegisterDetailResponse> getAllByUserIdBetween(int userId, LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByUserIdAndCreatedDateBetweenAndEnabledTrue(userId, startDate, endDate);
        return mapper.toResponses(details);
    }

    @Override
    public List<CashRegisterDetailResponse> getAllByUserIdWhereStatusIsNotBetween(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterStatus status) {
        final var details = repository.findAllByUserIdAndCreatedDateBetweenAndEnabledTrueAndCashRegisterStatusNot(userId, startDate, endDate, status);
        return mapper.toResponses(details);
    }

    @Override
    public List<CashRegisterDetailResponse> getAllOfToday() {
        final var today = LocalDate.now();
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now());

        return mapper.toResponses(repository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate));
    }

    @Override
    public Optional<CashRegisterDetailResponse> get(int cashRegisterDetailId) {
        final var detail = repository.findByIdAndEnabledTrue(cashRegisterDetailId);
        return detail.map(mapper::toResponse);
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
}
