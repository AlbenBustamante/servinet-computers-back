package com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.entity.CashRegisterDetailEntity;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserFullNameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CashRegisterDetailJpaRepository extends JpaRepository<CashRegisterDetailEntity, Integer> {
    Optional<CashRegisterDetailEntity> findByIdAndEnabledTrue(int id);

    Integer countByStatusNotAndEnabledTrue(CashRegisterDetailStatus status);

    boolean existsByUserIdAndCreatedDateBetweenAndEnabledTrueAndStatusNot(int userId, LocalDateTime firstDate, LocalDateTime lastDate, CashRegisterDetailStatus status);

    @Query("SELECT crd FROM CashRegisterDetail crd " +
            "WHERE crd.cashRegister.id = :cashRegisterId " +
            "AND crd.enabled = true " +
            "ORDER BY crd.createdDate DESC")
    Page<CashRegisterDetailEntity> findLatestByCashRegisterIdAndEnabledTrue(Integer cashRegisterId, Pageable pageable);

    List<CashRegisterDetailEntity> findAllByUserIdAndCreatedDateBetweenAndEnabledTrueAndStatusNot(int userId, LocalDateTime firstDate, LocalDateTime lastDate, CashRegisterDetailStatus status);

    List<CashRegisterDetailEntity> findAllByUserIdAndCreatedDateBetweenAndEnabledTrueOrderByCreatedDate(int userId, LocalDateTime firstDate, LocalDateTime lastDate);

    List<CashRegisterDetailEntity> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<CashRegisterDetailEntity> findAllByStatusNotAndEnabledTrueAndCreatedDateBefore(CashRegisterDetailStatus status, LocalDateTime createdDate);

    List<CashRegisterDetailEntity> findAllByCashRegisterIdAndEnabledTrue(int cashRegisterId);

    List<CashRegisterDetailEntity> findAllByUserIdNotAndEnabledTrueAndStatusNotAndCreatedDateBetween(int userId, CashRegisterDetailStatus status, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT crd FROM CashRegisterDetail crd " +
            "WHERE crd.cashRegister.id IN :cashRegisterIds " +
            "AND crd.enabled = true " +
            "AND crd.createdDate = (SELECT MAX(crd1.createdDate) FROM CashRegisterDetail crd1 WHERE crd1.cashRegister.id = crd.cashRegister.id)")
    List<CashRegisterDetailEntity> findLatestByCashRegisterIdInAndEnabledTrue(List<Integer> cashRegisterIds);

    @Query("SELECT crd FROM CashRegisterDetail crd " +
            "WHERE crd.cashRegister.id NOT IN :cashRegisterIds " +
            "AND crd.enabled = true " +
            "AND crd.createdDate = (SELECT MAX(crd1.createdDate) FROM CashRegisterDetail crd1 WHERE crd1.cashRegister.id = crd.cashRegister.id)")
    List<CashRegisterDetailEntity> findLatestByCashRegisterIdNotInAndEnabledTrue(List<Integer> cashRegisterIds);

    @Query("SELECT crd.finalBase FROM CashRegisterDetail crd " +
            "WHERE crd.cashRegister.id = :cashRegisterId AND " +
            "crd.enabled = true " +
            "ORDER BY crd.createdDate DESC")
    Page<String> findBaseByCashRegisterId(@Param("cashRegisterId") int cashRegisterId, Pageable pageable);

    @Query("SELECT new com.servinetcomputers.api.module.user.domain.dto.UserFullNameDto(crd.user.name, crd.user.lastName) FROM CashRegisterDetail crd " +
            "WHERE crd.id = :id AND " +
            "crd.enabled = true")
    Optional<UserFullNameDto> findUserFullNameById(int id);
}
