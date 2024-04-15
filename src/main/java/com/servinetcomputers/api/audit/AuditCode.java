package com.servinetcomputers.api.audit;

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

        final var code = auth.getPrincipal().toString();

        return Optional.of(code.equalsIgnoreCase("anonymousUser") ? "-" : code);
    }
}
