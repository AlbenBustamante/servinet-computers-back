package com.servinetcomputers.api.module.auth.application.service;

import com.servinetcomputers.api.core.email.EmailSenderService;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.auth.application.usecase.SendCodeToChangePasswordUseCase;
import com.servinetcomputers.api.module.auth.dto.RequestChangePasswordDto;
import com.servinetcomputers.api.module.passwordtempcode.application.usecase.CreatePasswordTempCodeUseCase;
import com.servinetcomputers.api.module.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendCodeToChangePasswordService implements SendCodeToChangePasswordUseCase {
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;
    private final CreatePasswordTempCodeUseCase createPasswordTempCodeUseCase;

    @Override
    public void call(RequestChangePasswordDto dto) {
        final var email = userRepository.getEmailByCode(dto.userCode())
                .orElseThrow(() -> new NotFoundException("No se encontró ningún email con el código ingresado: " + dto.userCode()));

        final var subject = "Solicitud Cambio de Clave";
        final var body = "El código que debes ingresar es: %s";
        final var code = createPasswordTempCodeUseCase.call(dto.userCode());

        emailSenderService.sendEmail(email, subject, String.format(body, code.getCode()));
    }
}
