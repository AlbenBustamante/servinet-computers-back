package com.servinetcomputers.api.module.cashregister.persistence;

import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaCashRegisterRepository extends JpaRepository<CashRegisterEntity, Integer> {
    Optional<CashRegisterEntity> findByIdAndEnabledTrue(int id);

    List<CashRegisterEntity> findAllByEnabledTrue();

    @Query("SELECT cr.id FROM CashRegister cr " +
            "WHERE cr.enabled = true")
    List<Integer> findAllIdsAndEnabledTrue();

    boolean existsByIdAndEnabledTrue(int id);

    boolean existsByNumeralAndEnabledTrue(int numeral);
}
