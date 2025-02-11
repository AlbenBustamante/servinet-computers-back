package com.servinetcomputers.api.module.cashtransfer.persistence;

import com.servinetcomputers.api.module.cashtransfer.persistence.entity.CashTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCashTransferRepository extends JpaRepository<CashTransfer, Integer> {
}
