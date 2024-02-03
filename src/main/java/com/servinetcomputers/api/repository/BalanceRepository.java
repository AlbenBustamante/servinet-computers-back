package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The {@link Balance} repository.
 */
public interface BalanceRepository extends JpaRepository<Balance, Integer> {

    /**
     * Search all the balances by the campus ID and the status.
     *
     * @param campusId    the ID of the campus to search.
     * @param isAvailable if is available.
     * @return all balances found.
     */
    List<Balance> findAllByCampusIdAndIsAvailable(int campusId, boolean isAvailable);

}
