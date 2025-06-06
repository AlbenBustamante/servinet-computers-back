package com.servinetcomputers.api.core.converter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

/**
 * Convertidor de {@link Role} a {@link String} y viceversa.
 */
@Converter
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
                .orElseThrow(() -> new NotFoundException("Role no encontrado"));
    }
}
