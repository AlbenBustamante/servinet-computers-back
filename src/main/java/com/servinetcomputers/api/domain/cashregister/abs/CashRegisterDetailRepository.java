package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CashRegisterDetailRepository extends JpaRepository<CashRegisterDetail, Integer> {

    boolean existsByCreatedByAndCreatedDateBetween(String createdBy, LocalDateTime firstDate, LocalDateTime lastDate);

    Optional<CashRegisterDetail> findByCreatedByAndCreatedDateBetween(String createdBy, LocalDateTime firstDate, LocalDateTime lastDate);

}
