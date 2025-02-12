package com.servinetcomputers.api.core.converter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class CashBoxTypeConverter implements AttributeConverter<CashBoxType, Character> {
    @Override
    public Character convertToDatabaseColumn(CashBoxType attribute) {
        return attribute.getType();
    }

    @Override
    public CashBoxType convertToEntityAttribute(Character dbData) {
        return Arrays.stream(CashBoxType.values())
                .filter(type -> type.getType().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró el tipo suministrado"));
    }
}
