package com.servinetcomputers.api.module.tempcode.persistence;

import com.servinetcomputers.api.module.tempcode.persistence.entity.TempCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTempCodeRepository extends JpaRepository<TempCode, Integer> {
    Optional<TempCode> findFirstByOrderByCreatedDateDesc();
}
