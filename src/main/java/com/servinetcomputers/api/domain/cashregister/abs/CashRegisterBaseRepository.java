package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashRegisterBaseRepository extends JpaRepository<CashRegisterBase, Integer> {

    List<CashRegisterBase> findByCashRegisterDetailIdAndEnabledTrue(int cashRegisterDetailId);

}
