package com.servinetcomputers.api.module.auth.application.service;

import com.servinetcomputers.api.core.email.EmailSenderService;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.auth.application.usecase.SendCodeToChangePasswordUseCase;
import com.servinetcomputers.api.module.auth.dto.RequestChangePasswordDto;
import com.servinetcomputers.api.module.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@RequiredArgsConstructor
@Service
public class SendCodeToChangePasswordService implements SendCodeToChangePasswordUseCase {
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;

    @Override
    public void call(RequestChangePasswordDto dto) {
        final var email = userRepository.getEmailByCode(dto.userCode())
                .orElseThrow(() -> new NotFoundException("No se encontró ningún email con el código ingresado: " + dto.userCode()));

        final var subject = "Solicitud Cambio de Clave";
        final var body = "El código que debes ingresar es: %d";

        emailSenderService.sendEmail(email, subject, String.format(body, randomCode()));
    }

    private Integer randomCode() {
        final var random = new SecureRandom();
        final var min = 1000;
        final var max = 10000;

        return random.nextInt(max - min + 1) + min;
    }
}
