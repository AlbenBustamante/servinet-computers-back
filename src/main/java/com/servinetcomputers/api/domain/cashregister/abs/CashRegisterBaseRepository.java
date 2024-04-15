package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CashRegisterBaseRepository extends JpaRepository<CashRegisterBase, Integer> {

    Optional<CashRegisterBase> findByCashRegisterDetailIdAndEnabledTrue(int cashRegisterDetailId);

}
