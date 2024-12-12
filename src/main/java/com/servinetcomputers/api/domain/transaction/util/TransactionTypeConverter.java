package com.servinetcomputers.api.domain.transaction.util;

import com.servinetcomputers.api.core.exception.NotFoundException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class TransactionTypeConverter implements AttributeConverter<TransactionType, Character> {
    @Override
    public Character convertToDatabaseColumn(TransactionType attribute) {
        return attribute.getType();
    }

    @Override
    public TransactionType convertToEntityAttribute(Character dbData) {
        return Arrays.stream(TransactionType.values())
                .filter(type -> type.getType().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Tipo de transacción no encontrado"));
    }
}
