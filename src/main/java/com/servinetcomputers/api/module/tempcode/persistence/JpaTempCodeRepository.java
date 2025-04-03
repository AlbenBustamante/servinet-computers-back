package com.servinetcomputers.api.module.tempcode.persistence;

import com.servinetcomputers.api.module.tempcode.persistence.entity.TempCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTempCodeRepository extends JpaRepository<TempCode, Integer> {
}
