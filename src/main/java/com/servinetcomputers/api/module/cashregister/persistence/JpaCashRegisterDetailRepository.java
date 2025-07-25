package com.servinetcomputers.api.module.cashregister.persistence;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import com.servinetcomputers.api.module.user.domain.dto.UserFullNameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaCashRegisterDetailRepository extends JpaRepository<CashRegisterDetail, Integer> {
    Optional<CashRegisterDetail> findByIdAndEnabledTrue(int id);

    Integer countByStatusNotAndEnabledTrue(CashRegisterDetailStatus status);

    boolean existsByUserIdAndCreatedDateBetweenAndEnabledTrueAndStatusNot(int userId, LocalDateTime firstDate, LocalDateTime lastDate, CashRegisterDetailStatus status);

    List<CashRegisterDetail> findAllByUserIdAndCreatedDateBetweenAndEnabledTrueAndStatusNot(int userId, LocalDateTime firstDate, LocalDateTime lastDate, CashRegisterDetailStatus status);

    List<CashRegisterDetail> findAllByUserIdAndCreatedDateBetweenAndEnabledTrueOrderByCreatedDate(int userId, LocalDateTime firstDate, LocalDateTime lastDate);

    List<CashRegisterDetail> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<CashRegisterDetail> findAllByCashRegisterIdAndEnabledTrueAndCreatedDateBetween(int cashRegisterId, LocalDateTime startDate, LocalDateTime endDate);

    List<CashRegisterDetail> findAllByUserIdNotAndEnabledTrueAndStatusNotAndCreatedDateBetween(int userId, CashRegisterDetailStatus status, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT crd FROM CashRegisterDetail crd " +
            "WHERE crd.cashRegister.id IN :cashRegisterIds " +
            "AND crd.enabled = true " +
            "AND crd.createdDate = (SELECT MAX(crd1.createdDate) FROM CashRegisterDetail crd1 WHERE crd1.cashRegister.id = crd.cashRegister.id)")
    List<CashRegisterDetail> findLatestByCashRegisterIdInAndEnabledTrue(List<Integer> cashRegisterIds);

    @Query("SELECT crd.finalBase FROM CashRegisterDetail crd " +
            "WHERE crd.cashRegister.id = :cashRegisterId AND " +
            "crd.enabled = true " +
            "ORDER BY crd.createdDate DESC")
    Page<String> findBaseByCashRegisterId(@Param("cashRegisterId") int cashRegisterId, Pageable pageable);

    @Query("SELECT new com.servinetcomputers.api.module.user.domain.dto.UserFullNameDto(crd.user.name, crd.user.lastName) FROM CashRegisterDetail crd " +
            "WHERE crd.id = :id AND " +
            "crd.enabled = true")
    Optional<UserFullNameDto> findUserFullNameById(int id);

    /*
     * Encuentra el estado más reciente según el ID de una caja registradora.
     *
     * @param cashRegisterId {@code ID} de caja registradora.
     * @param pageable       {@link Pageable} para limitar la cantidad de registros.
     * @return {@link Page} de {@link CashRegisterDetailStatus}.
     */
    /*@Query("SELECT crd.status FROM CashRegisterDetail crd " +
            "WHERE crd.cashRegister.id = :cashRegisterId " +
            "AND crd.enabled = true " +
            "ORDER BY crd.createdDate DESC")
    Page<CashRegisterDetailStatus> findLatestStatusByCashRegisterId(Integer cashRegisterId, Pageable pageable);*/
}
