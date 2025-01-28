package com.servinetcomputers.api.module.cashregister.persistence;

import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCashRegisterRepository extends JpaRepository<CashRegister, Integer> {
    Optional<CashRegister> findByIdAndEnabledTrue(int id);

    List<CashRegister> findAllByEnabledTrue();

    boolean existsByNumeralAndEnabledTrue(int numeral);
}
