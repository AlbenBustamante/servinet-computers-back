package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CashRegisterRepository extends JpaRepository<CashRegister, Integer> {

    boolean existsByNumeral(int numeral);

    Optional<CashRegister> findByNumeral(int numeral);

}
