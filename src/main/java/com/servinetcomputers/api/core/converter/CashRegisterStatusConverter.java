package com.servinetcomputers.api.core.converter;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class CashRegisterStatusConverter implements AttributeConverter<CashRegisterStatus, Character> {
    @Override
    public Character convertToDatabaseColumn(CashRegisterStatus cashRegisterStatus) {
        return cashRegisterStatus.getStatus();
    }

    @Override
    public CashRegisterStatus convertToEntityAttribute(Character character) {
        return Arrays.stream(CashRegisterStatus.values())
                .filter(status -> status.getStatus().equals(character))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Estado de caja no encontrado"));
    }
}
