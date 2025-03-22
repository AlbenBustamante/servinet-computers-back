package com.servinetcomputers.api.module.cashregister.persistence.repository;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.cashregister.persistence.JpaCashRegisterDetailRepository;
import com.servinetcomputers.api.module.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.module.user.domain.dto.UserFullNameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CashRegisterDetailRepositoryImpl implements CashRegisterDetailRepository {
    private final JpaCashRegisterDetailRepository repository;
    private final CashRegisterDetailMapper mapper;

    @Override
    public void save(CreateCashRegisterDetailDto request) {
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
    public List<CashRegisterDetailResponse> getAllBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return mapper.toResponses(details);
    }

    @Override
    public List<CashRegisterDetailResponse> getAllByStatusNotAndBefore(CashRegisterDetailStatus status, LocalDateTime createdDate) {
        final var details = repository.findAllByStatusNotAndEnabledTrueAndCreatedDateBefore(status, createdDate);
        return mapper.toResponses(details);
    }

    @Override
    public List<CashRegisterDetailResponse> getAllByCashRegisterId(int cashRegisterId) {
        final var details = repository.findAllByCashRegisterIdAndEnabledTrue(cashRegisterId);
        return mapper.toResponses(details);
    }

    @Override
    public List<CashRegisterDetailResponse> getAllWhereUserIdIsNotAndStatusAndBetween(int userId, CashRegisterDetailStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByUserIdNotAndEnabledTrueAndStatusAndCreatedDateBetween(userId, status, startDate, endDate);
        return mapper.toResponses(details);
    }

    @Override
    public List<CashRegisterDetailResponse> getLatestWhereCashRegisterIdIsIn(List<Integer> cashRegisterIds) {
        final var details = repository.findLatestByCashRegisterIdInAndEnabledTrue(cashRegisterIds);
        return mapper.toResponses(details);
    }

    @Override
    public List<CashRegisterDetailResponse> getLatestWhereCashRegisterIdIsNotIn(List<Integer> cashRegisterIds) {
        final var details = repository.findLatestByCashRegisterIdNotInAndEnabledTrue(cashRegisterIds);
        return mapper.toResponses(details);
    }

    @Override
    public Optional<CashRegisterDetailResponse> get(int cashRegisterDetailId) {
        final var detail = repository.findByIdAndEnabledTrue(cashRegisterDetailId);
        return detail.map(mapper::toResponse);
    }

    @Override
    public Optional<UserFullNameDto> getUserFullNameById(int cashRegisterDetailId) {
        return repository.findUserFullNameById(cashRegisterDetailId);
    }
}
