package com.servinetcomputers.api.module.passwordtempcode.domain.adapter;

import com.servinetcomputers.api.module.passwordtempcode.domain.dto.CreatePasswordTempCodeDto;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.PasswordTempCodeDto;

import java.util.List;

public interface PasswordTempCodePersistenceAdapter {
    PasswordTempCodeDto save(CreatePasswordTempCodeDto dto);

    boolean existsByCode(String code);

    List<PasswordTempCodeDto> getAllByUserCode(String userCode);

    void setUsedBy(Integer passwordTempCodeId, String userCode);
}
