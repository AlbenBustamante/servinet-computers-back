package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The {@link Transfer} repository.
 */
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    /**
     * Search all the transfers created between two dates.
     *
     * @param firstDate   the first date.
     * @param lastDate    the second and last date.
     * @param IsAvailable to check if the transfer is enabled.
     * @return a list of all the transfers found.
     */
    List<Transfer> findAllByCreatedAtBetweenAndIsAvailable(LocalDateTime firstDate, LocalDateTime lastDate, boolean IsAvailable);

}
