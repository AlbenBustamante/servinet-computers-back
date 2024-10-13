package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CashRegisterDetailRepository extends JpaRepository<CashRegisterDetail, Integer> {

    boolean existsByIdAndEnabledTrue(int id);

    Optional<CashRegisterDetail> findByIdAndEnabledTrue(int id);

    boolean existsByUserIdAndCreatedDateBetweenAndEnabledTrue(int userId, LocalDateTime firstDate, LocalDateTime lastDate);

    List<CashRegisterDetail> findAllByUserIdAndCreatedDateBetweenAndEnabledTrue(int userId, LocalDateTime firstDate, LocalDateTime lastDate);

    @Query("SELECT crd.finalBase FROM CashRegisterDetail crd " +
            "WHERE crd.cashRegister.id = :cashRegisterId AND " +
            "crd.enabled = true " +
            "ORDER BY crd.createdDate DESC")
    Page<String> findBaseByCashRegisterId(@Param("cashRegisterId") int cashRegisterId, Pageable pageable);

}
