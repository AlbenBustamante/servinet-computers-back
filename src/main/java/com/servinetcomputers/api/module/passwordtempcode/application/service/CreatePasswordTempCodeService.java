package com.servinetcomputers.api.module.passwordtempcode.application.service;

import com.servinetcomputers.api.module.passwordtempcode.application.usecase.CreatePasswordTempCodeUseCase;
import com.servinetcomputers.api.module.passwordtempcode.domain.adapter.PasswordTempCodePersistenceAdapter;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.CreatePasswordTempCodeDto;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.PasswordTempCodeDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import static com.servinetcomputers.api.core.util.constants.PasswordTempCodeConstants.CODE_LENGTH;

@RequiredArgsConstructor
@Service
public class CreatePasswordTempCodeService implements CreatePasswordTempCodeUseCase {
    private final PasswordTempCodePersistenceAdapter adapter;

    @Override
    public PasswordTempCodeDto call(String userCode) {
        var code = randomCode();

        while (adapter.existsByCode(code)) {
            code = randomCode();
        }

        return adapter.save(new CreatePasswordTempCodeDto(code, userCode));
    }

    private String randomCode() {
        return RandomStringUtils.randomAlphanumeric(CODE_LENGTH);
    }
}
