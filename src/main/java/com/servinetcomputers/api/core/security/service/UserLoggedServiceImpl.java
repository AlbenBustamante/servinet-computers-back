package com.servinetcomputers.api.core.security.service;

import com.servinetcomputers.api.module.user.domain.dto.UserResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserLoggedServiceImpl implements UserLoggedService {
    @Override
    public int id() {
        return principal().getId();
    }

    @Override
    public String code() {
        return principal().getCode();
    }

    private UserResponse principal() {
        final var auth = SecurityContextHolder.getContext().getAuthentication();
        final var principal = auth.getPrincipal();

        return (UserResponse) principal;
    }
}
