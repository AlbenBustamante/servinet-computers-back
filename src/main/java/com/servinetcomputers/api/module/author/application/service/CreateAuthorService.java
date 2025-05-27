package com.servinetcomputers.api.module.author.application.service;

import com.servinetcomputers.api.core.exception.AlreadyExistsException;
import com.servinetcomputers.api.module.author.application.port.out.AuthorOutputPort;
import com.servinetcomputers.api.module.author.application.port.out.CreateAuthorCommand;
import com.servinetcomputers.api.module.author.application.usecase.CreateAuthorUseCase;
import com.servinetcomputers.api.module.author.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateAuthorService implements CreateAuthorUseCase {
    private final AuthorOutputPort port;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Author create(CreateAuthorCommand command) {
        if (port.existsByName(command.getName())) {
            throw new AlreadyExistsException("Ya existe un autor con ese nombre: " + command.getName());
        }

        return port.create(command);
    }
}
