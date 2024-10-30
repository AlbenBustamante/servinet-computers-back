package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.entity.SafeBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SafeBaseRepository extends JpaRepository<SafeBase, Integer> {

    @Query("SELECT sb.id FROM SafeBase sb")
    List<Integer> findAllIds();

    @Query("SELECT sb.base FROM SafeBase sb " +
            "WHERE sb.id = :id " +
            "AND sb.enabled = true " +
            "AND sb.createdDate BETWEEN :startDate AND :lastDate " +
            "ORDER BY sb.createdDate DESC")
    Page<String> findLastBase(int id, LocalDateTime startDate, LocalDateTime lastDate, Pageable pageable);

}
