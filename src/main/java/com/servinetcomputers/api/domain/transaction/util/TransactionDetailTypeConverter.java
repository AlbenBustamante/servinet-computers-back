package com.servinetcomputers.api.domain.transaction.util;

import com.servinetcomputers.api.core.exception.NotFoundException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class TransactionDetailTypeConverter implements AttributeConverter<TransactionDetailType, Character> {
    @Override
    public Character convertToDatabaseColumn(TransactionDetailType attribute) {
        return attribute.getType();
    }

    @Override
    public TransactionDetailType convertToEntityAttribute(Character dbData) {
        return Arrays.stream(TransactionDetailType.values())
                .filter(type -> type.getType().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(""));
    }
}
