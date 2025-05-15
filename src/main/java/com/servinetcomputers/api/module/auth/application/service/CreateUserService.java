package com.servinetcomputers.api.module.auth.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.email.EmailSenderService;
import com.servinetcomputers.api.core.exception.AlreadyExistsException;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.module.auth.application.usecase.CreateUserUseCase;
import com.servinetcomputers.api.module.user.domain.dto.CreateUserDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;
import com.servinetcomputers.api.module.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class CreateUserService implements CreateUserUseCase {
    private static final String SUBJECT = "Bienvenido a Servinet Computers";

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    private final DateTimeService dateTimeService;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public UserDto call(CreateUserDto dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new AlreadyExistsException("Ya existe un usuario con este correo: " + dto.getEmail());
        }

        final var tempPassword = "123456";
        dto.setPassword(passwordEncoder.encode(tempPassword));

        final var lastUser = repository.getLastByRole(dto.getRole());
        final var role = dto.getRole().getRole().toLowerCase();

        final var code = lastUser.map(user -> {
            final var numberCode = user.getCode().split(role)[1];
            return Integer.parseInt(numberCode);
        }).orElse(0);

        dto.setCode(role + (code + 1));

        final var body = "%s %s, te has registrado con éxito el día %s!";
        final var to = dto.getEmail();

        emailSenderService.sendEmail(to, SUBJECT, String.format(body, dto.getName(), dto.getLastName(), now()));

        return repository.save(dto);
    }

    private String now() {
        return dateTimeService.now().format(DateTimeFormatter.ofPattern("eee dd MM yyyy, HH:mm:ss a"));
    }
}
