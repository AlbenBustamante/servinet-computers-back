package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@link Transfer} repository.
 */
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
