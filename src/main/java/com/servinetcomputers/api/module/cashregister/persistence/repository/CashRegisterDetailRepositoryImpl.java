package com.servinetcomputers.api.module.cashregister.persistence.repository;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
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
    public CashRegisterDetailDto save(CashRegisterDetailDto response) {
        final var entity = mapper.toEntity(response);
        final var newDetail = repository.save(entity);

        return mapper.toDto(newDetail);
    }

    @Override
    public Integer getCurrentAmount() {
        final var currentAmount = repository.countByStatusNotAndEnabledTrue(CashRegisterDetailStatus.CLOSED);
        return currentAmount != null ? currentAmount : 0;
    }

    @Override
    public boolean existsByUserIdAndStatusNot(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterDetailStatus status) {
        return repository.existsByUserIdAndCreatedDateBetweenAndEnabledTrueAndStatusNot(userId, startDate, endDate, status);
    }

    @Override
    public List<CashRegisterDetailDto> getAllByUserIdBetween(int userId, LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByUserIdAndCreatedDateBetweenAndEnabledTrueOrderByCreatedDate(userId, startDate, endDate);
        return mapper.toDto(details);
    }

    @Override
    public List<CashRegisterDetailDto> getAllByUserIdWhereStatusIsNotBetween(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterDetailStatus status) {
        final var details = repository.findAllByUserIdAndCreatedDateBetweenAndEnabledTrueAndStatusNot(userId, startDate, endDate, status);
        return mapper.toDto(details);
    }

    @Override
    public List<CashRegisterDetailDto> getAllBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return mapper.toDto(details);
    }

    @Override
    public List<CashRegisterDetailDto> getAllByCashRegisterIdBetween(int cashRegisterId, LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByCashRegisterIdAndEnabledTrueAndCreatedDateBetween(cashRegisterId, startDate, endDate);
        return mapper.toDto(details);
    }

    @Override
    public List<CashRegisterDetailDto> getAllWhereUserIdIsNotAndStatusNotAndBetween(int userId, CashRegisterDetailStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByUserIdNotAndEnabledTrueAndStatusNotAndCreatedDateBetween(userId, status, startDate, endDate);
        return mapper.toDto(details);
    }

    @Override
    public List<CashRegisterDetailDto> getLatestWhereCashRegisterIdIsIn(List<Integer> cashRegisterIds) {
        final var details = repository.findLatestByCashRegisterIdInAndEnabledTrue(cashRegisterIds);
        return mapper.toDto(details);
    }

    /*@Override
    public Optional<CashRegisterDetailStatus> getLatestStatusByCashRegisterId(Integer cashRegisterId) {
        final var page = repository.findLatestStatusByCashRegisterId(cashRegisterId, PageRequest.of(0, 1));
        return Optional.of(page.getContent().get(0));
    }*/

    @Override
    public Optional<CashRegisterDetailDto> get(int cashRegisterDetailId) {
        final var detail = repository.findByIdAndEnabledTrue(cashRegisterDetailId);
        return detail.map(mapper::toDto);
    }

    @Override
    public Optional<UserFullNameDto> getUserFullNameById(int cashRegisterDetailId) {
        return repository.findUserFullNameById(cashRegisterDetailId);
    }
}
