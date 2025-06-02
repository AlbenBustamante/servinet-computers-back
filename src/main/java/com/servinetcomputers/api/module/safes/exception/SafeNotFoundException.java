package com.servinetcomputers.api.module.safes.exception;

import com.servinetcomputers.api.core.exception.NotFoundException;

public class SafeNotFoundException extends NotFoundException {
    public SafeNotFoundException(Integer safeId) {
        super("No se encontró la caja fuerte ID: " + safeId);
    }
}
