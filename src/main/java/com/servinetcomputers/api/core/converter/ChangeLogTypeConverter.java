package com.servinetcomputers.api.core.converter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class ChangeLogTypeConverter implements AttributeConverter<ChangeLogType, Character> {
    @Override
    public Character convertToDatabaseColumn(ChangeLogType attribute) {
        return attribute.getType();
    }

    @Override
    public ChangeLogType convertToEntityAttribute(Character dbData) {
        return Arrays.stream(ChangeLogType.values())
                .filter(changeLogType -> changeLogType.getType().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró el tipo: " + dbData));
    }
}
