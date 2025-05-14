package com.servinetcomputers.api.module.passwordtempcode.application.service;

import com.servinetcomputers.api.module.passwordtempcode.application.usecase.CreatePasswordTempCodeUseCase;
import com.servinetcomputers.api.module.passwordtempcode.domain.adapter.PasswordTempCodePersistenceAdapter;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.CreatePasswordTempCodeDto;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.PasswordTempCodeDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePasswordTempCodeService implements CreatePasswordTempCodeUseCase {
    private final PasswordTempCodePersistenceAdapter adapter;

    @Override
    public PasswordTempCodeDto call() {
        var code = randomCode();

        while (adapter.isUsed(code)) {
            code = randomCode();
        }

        return adapter.save(new CreatePasswordTempCodeDto(code));
    }

    private String randomCode() {
        final var length = 5;
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
