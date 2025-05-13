package com.servinetcomputers.api.core.audit;

import com.servinetcomputers.api.module.user.domain.dto.UserDto;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditCode implements AuditorAware<String> {
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
