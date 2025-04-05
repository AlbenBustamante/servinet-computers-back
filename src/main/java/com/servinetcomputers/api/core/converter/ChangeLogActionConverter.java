package com.servinetcomputers.api.core.converter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class ChangeLogActionConverter implements AttributeConverter<ChangeLogAction, Character> {
    @Override
    public Character convertToDatabaseColumn(ChangeLogAction attribute) {
        return attribute.getType();
    }

    @Override
    public ChangeLogAction convertToEntityAttribute(Character dbData) {
        return Arrays.stream(ChangeLogAction.values())
                .filter(changeLogAction -> changeLogAction.getType().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró la acción: " + dbData));
    }
}
