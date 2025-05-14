package com.servinetcomputers.api.module.passwordtempcode.persistence;

import com.servinetcomputers.api.module.passwordtempcode.persistence.entity.PasswordTempCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPasswordTempCodeRepository extends JpaRepository<PasswordTempCode, Integer> {
    boolean existsByCodeAndEnabledTrueAndUsedByIsNull(String code);
}
