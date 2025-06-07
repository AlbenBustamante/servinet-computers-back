package com.servinetcomputers.api.module.safes.exception;

import com.servinetcomputers.api.core.exception.NotFoundException;

public class SafeDetailNotFoundException extends NotFoundException {
    public SafeDetailNotFoundException(Integer safeDetailId) {
        super("No se encontró el movimiento de caja con ID: " + safeDetailId);
    }
}
