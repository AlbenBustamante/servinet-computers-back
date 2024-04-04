package com.servinetcomputers.api.domain.balance.abs;

import com.servinetcomputers.api.domain.balance.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
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
    List<Balance> findAllByCampusIdAndIsAvailableAndCreatedAtBetween(int campusId, boolean isAvailable, LocalDateTime startDate, LocalDateTime endDate);

}
