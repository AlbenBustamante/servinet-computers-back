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
     * @param firstDate the first date.
     * @param lastDate  the second and last date.
     * @return a list of all the transfers found.
     */
    List<Transfer> findAllByCreatedAtBetween(LocalDateTime firstDate, LocalDateTime lastDate);

}
