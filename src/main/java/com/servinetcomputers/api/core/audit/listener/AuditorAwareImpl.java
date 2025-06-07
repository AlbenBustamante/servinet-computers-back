package com.servinetcomputers.api.core.audit.listener;

import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserDto;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        final var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return Optional.of("-");
        }

        final var principal = auth.getPrincipal();

        if (principal == "anonymousUser") {
            return Optional.of("-");
        }

        final var user = (UserDto) principal;
        final var code = user.getCode();

        return Optional.of(code);
    }
}
