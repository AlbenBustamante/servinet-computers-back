package com.servinetcomputers.api.module.safes.exception;

import com.servinetcomputers.api.core.exception.NotFoundException;

public class SafeDetailBySafeIdNotFoundException extends NotFoundException {
    public SafeDetailBySafeIdNotFoundException(Integer safeId) {
        super("No se encontró movimientos para la caja fuerte con ID: " + safeId);
    }
}
