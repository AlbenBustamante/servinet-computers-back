package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The {@link Balance} repository.
 */
public interface BalanceRepository extends JpaRepository<Balance, Integer> {

    /**
     * Search all the balances by the campus ID and the status.
     *
     * @param isAvailable if is available.
     * @return an {@link Optional} of all the balances found.
     */
    Optional<List<Balance>> findAllByCampusIdAndIsAvailable(boolean isAvailable);

}
