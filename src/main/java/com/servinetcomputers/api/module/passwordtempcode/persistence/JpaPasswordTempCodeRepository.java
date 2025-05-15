package com.servinetcomputers.api.module.passwordtempcode.persistence;

import com.servinetcomputers.api.module.passwordtempcode.persistence.entity.PasswordTempCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPasswordTempCodeRepository extends JpaRepository<PasswordTempCode, Integer> {
    boolean existsByCode(String code);

    List<PasswordTempCode> findAllByUserCode(String userCode);
}
