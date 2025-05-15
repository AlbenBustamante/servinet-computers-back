package com.servinetcomputers.api.module.auth.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.auth.application.usecase.ChangePasswordUseCase;
import com.servinetcomputers.api.module.auth.dto.ChangePasswordDto;
import com.servinetcomputers.api.module.passwordtempcode.domain.adapter.PasswordTempCodePersistenceAdapter;
import com.servinetcomputers.api.module.passwordtempcode.domain.dto.PasswordTempCodeDto;
import com.servinetcomputers.api.module.user.domain.adapter.UserPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class ChangePasswordService implements ChangePasswordUseCase {
    private final UserPersistenceAdapter userPersistenceAdapter;
    private final PasswordTempCodePersistenceAdapter passwordTempCodePersistenceAdapter;
    private final PasswordEncoder passwordEncoder;
    private final DateTimeService dateTimeService;

    @Override
    public void call(ChangePasswordDto dto) {
        if (!dto.passwordsDoMatch()) {
            throw new BadRequestException("Las contraseñas no coinciden");
        }

        if (!userPersistenceAdapter.existsByCode(dto.userCode())) {
            throw new NotFoundException("No se encontró al usuario solicitado: " + dto.userCode());
        }

        final var tempCodes = passwordTempCodePersistenceAdapter.getAllByUserCode(dto.userCode());

        if (tempCodes.isEmpty()) {
            throw new NotFoundException("Por favor, solicita un código temporal");
        }

        final var tempCode = validate(dto.tempCode(), tempCodes);

        final var encodedPassword = passwordEncoder.encode(dto.newPassword());
        userPersistenceAdapter.savePassword(dto.userCode(), encodedPassword);
        passwordTempCodePersistenceAdapter.setUsedBy(tempCode.getId(), dto.userCode());
    }

    private PasswordTempCodeDto validate(String code, List<PasswordTempCodeDto> tempCodes) {
        final var tempCodeFound = tempCodes.stream()
                .filter(t -> t.getCode().equals(code))
                .findFirst();

        if (tempCodeFound.isEmpty()) {
            throw new BadRequestException("El código ingresado no existe");
        }

        final var tempCode = tempCodeFound.get();

        final var now = dateTimeService.now();

        if (tempCode.getExpirationDate().isBefore(now)) {
            throw new BadRequestException("El código ha expirado, por favor solicita uno nuevo");
        }

        if (tempCode.getUsedById() != null) {
            throw new BadRequestException("El código ya ha sido utilizado, por favor solicita uno nuevo");
        }

        return tempCode;
    }
}
