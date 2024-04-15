package com.servinetcomputers.api.domain.cashregister.util;

import com.servinetcomputers.api.exception.NotFoundException;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

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
