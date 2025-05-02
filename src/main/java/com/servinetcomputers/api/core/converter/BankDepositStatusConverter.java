package com.servinetcomputers.api.core.converter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class BankDepositStatusConverter implements AttributeConverter<BankDepositStatus, Character> {
    @Override
    public Character convertToDatabaseColumn(BankDepositStatus attribute) {
        return attribute.getStatus();
    }

    @Override
    public BankDepositStatus convertToEntityAttribute(Character dbData) {
        return Arrays.stream(BankDepositStatus.values())
                .filter(status -> status.getStatus().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró el estado solicitado: " + dbData));
    }
}
