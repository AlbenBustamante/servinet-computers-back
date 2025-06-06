package com.servinetcomputers.api.module.user.application.service;

import com.google.api.client.util.Value;
import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.email.EmailSenderService;
import com.servinetcomputers.api.module.user.application.port.in.CreateUserUseCase;
import com.servinetcomputers.api.module.user.application.port.in.command.CreateUserCommand;
import com.servinetcomputers.api.module.user.application.port.out.UserReadPort;
import com.servinetcomputers.api.module.user.application.port.out.UserWritePort;
import com.servinetcomputers.api.module.user.domain.User;
import com.servinetcomputers.api.module.user.exception.EmailAlreadyRegisteredException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CreateUserService implements CreateUserUseCase {
    private static final String SUBJECT = "Bienvenido a Servinet Computers";

    @Value("${temp-password}")
    private String tempPassword;

    private final UserWritePort writePort;
    private final UserReadPort readPort;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    private final DateTimeService dateTimeService;

    @Override
    public User create(CreateUserCommand command) {
        if (readPort.existsByEmail(command.email())) {
            throw new EmailAlreadyRegisteredException();
        }

        final var lastUser = readPort.getLastByRole(command.role());
        final var role = command.role().getRole().toLowerCase();

        final var code = lastUser.map(user -> {
            final var numberCode = user.code().split(role)[1];
            return Integer.parseInt(numberCode);
        }).orElse(0);

        final var generatedCode = role + (code + 1);
        final var password = passwordEncoder.encode(tempPassword);
        final var user = User.create(command.name(), command.lastName(), command.email(), password, generatedCode, command.role());
        final var newUser = writePort.save(user);

        final var body = "%s %s, te has registrado con éxito el día %s!";
        final var to = newUser.email();
        final var now = dateTimeService.formattedNow();

        emailSenderService.sendEmail(to, SUBJECT, String.format(body, newUser.name(), newUser.lastName(), now));

        return newUser;
    }
}
