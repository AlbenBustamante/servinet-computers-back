package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@link Campus} repository.
 */
public interface CampusRepository extends JpaRepository<Campus, Integer> {

    /**
     * @param address the address to be checked.
     * @return {@code true} if the address already exists.
     */
    boolean existsByAddress(String address);

    /**
     * @param cellphone the cellphone to be checked.
     * @return {@code true} if the cellphone already exists.
     */
    boolean existsByCellphone(String cellphone);

    /**
     * @param terminal the terminal to be checked.
     * @return {@code true} if the terminal already exists.
     */
    boolean existsByTerminal(String terminal);

    /**
     * @param numeral the numeral to be checked.
     * @return {@code true} if the numeral already exists.
     */
    boolean existsByNumeral(int numeral);

}
