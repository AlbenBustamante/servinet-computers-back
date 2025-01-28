package com.servinetcomputers.api.module.safes.persistence;

import com.servinetcomputers.api.module.safes.persistence.entity.SafeBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaSafeBaseRepository extends JpaRepository<SafeBase, Integer> {
    Optional<SafeBase> findFirstBySafeDetailSafeIdAndEnabledTrueOrderByCreatedDateDesc(int id);
}
