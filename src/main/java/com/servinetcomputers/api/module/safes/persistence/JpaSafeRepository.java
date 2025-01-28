package com.servinetcomputers.api.module.safes.persistence;

import com.servinetcomputers.api.module.safes.persistence.entity.Safe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaSafeRepository extends JpaRepository<Safe, Integer> {
    boolean existsByNumeralAndEnabledTrue(int numeral);

    List<Safe> findAllByEnabledTrue();

    @Query("SELECT s.id FROM Safe s " +
            "WHERE s.enabled = true")
    List<Integer> findAllIds();
}
