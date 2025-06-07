package com.servinetcomputers.api.module.user.exception;

import com.servinetcomputers.api.core.exception.AlreadyExistsException;

public class EmailAlreadyRegisteredException extends AlreadyExistsException {
    public EmailAlreadyRegisteredException() {
        super("El email ingresado ya se encuentra en uso");
    }
}
