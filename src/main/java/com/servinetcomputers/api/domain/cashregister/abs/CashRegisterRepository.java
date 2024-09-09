package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.entity.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CashRegisterRepository extends JpaRepository<CashRegister, Integer> {

    List<CashRegister> findAllByEnabledTrue();

    boolean existsByNumeralAndEnabledTrue(int numeral);

    Optional<CashRegister> findByNumeralAndEnabledTrue(int numeral);

}
