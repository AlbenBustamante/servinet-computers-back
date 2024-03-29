package com.servinetcomputers.api.domain.user.util;

import com.servinetcomputers.api.exception.BadRequestException;
import com.servinetcomputers.api.security.util.Role;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        return role.getRole();
    }

    @Override
    public Role convertToEntityAttribute(String s) {
        return Arrays.stream(Role.values())
                .filter(role -> role.getRole().equals(s))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Role no encontrado"));
    }
}
