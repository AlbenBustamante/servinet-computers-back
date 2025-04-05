package com.servinetcomputers.api.module.changelog.persistence;

import com.servinetcomputers.api.module.changelog.persistence.entity.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaChangeLogRepository extends JpaRepository<ChangeLog, Integer> {
    List<ChangeLog> findAllByCashRegisterDetailIdAndEnabledTrue(int cashRegisterDetailId);
}
