package com.servinetcomputers.api.module.user.exception;

import com.servinetcomputers.api.core.exception.NotFoundException;

public class EmailNotFoundByCodeException extends NotFoundException {
    public EmailNotFoundByCodeException(String code) {
        super("No se encontró ningún email asociado al código solicitado: " + code);
    }
}
