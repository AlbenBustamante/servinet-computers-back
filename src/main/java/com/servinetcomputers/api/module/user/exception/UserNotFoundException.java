package com.servinetcomputers.api.module.user.exception;

import com.servinetcomputers.api.core.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Integer id) {
        super("No se encontró al usuario solicitado con el ID: " + id);
    }
    
    public UserNotFoundException(String code) {
        super("No se encontró al usuario solicitado con el código: " + code);
    }
}
