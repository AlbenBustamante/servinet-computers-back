package com.servinetcomputers.api.domain.transfer.abs;

import com.servinetcomputers.api.domain.transfer.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * @param pageable  the pageable.
     * @return a {@link Page} of all the transfers found.
     */
    Page<Transfer> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime firstDate, LocalDateTime lastDate, Pageable pageable);

    /**
     * Search all the transfers created between two dates without pageable.
     *
     * @param firstDate the first date.
     * @param lastDate  the last date.
     * @return all the transfers found.
     */
    List<Transfer> findAllByEnabledTrueAndCreatedDateBetween(LocalDateTime firstDate, LocalDateTime lastDate);

}
