package com.servinetcomputers.api.core.converter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class CashRegisterDetailStatusConverter implements AttributeConverter<CashRegisterDetailStatus, Character> {
    @Override
    public Character convertToDatabaseColumn(CashRegisterDetailStatus cashRegisterDetailStatus) {
        return cashRegisterDetailStatus.getStatus();
    }

    @Override
    public CashRegisterDetailStatus convertToEntityAttribute(Character character) {
        return Arrays.stream(CashRegisterDetailStatus.values())
                .filter(status -> status.getStatus().equals(character))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Estado de caja en funcionamiento no encontrado"));
    }
}
