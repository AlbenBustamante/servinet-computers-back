package com.servinetcomputers.api.module.passwordtempcode.domain.adapter;

import com.servinetcomputers.api.module.passwordtempcode.domain.dto.CreatePasswordTempCodeDto;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.PasswordTempCodeDto;

public interface PasswordTempCodePersistenceAdapter {
    PasswordTempCodeDto save(CreatePasswordTempCodeDto dto);

    boolean isUsed(String code);
}
