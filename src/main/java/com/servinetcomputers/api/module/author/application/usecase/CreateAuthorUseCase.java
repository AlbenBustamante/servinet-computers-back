package com.servinetcomputers.api.module.author.application.usecase;

import com.servinetcomputers.api.module.author.application.port.out.CreateAuthorCommand;
import com.servinetcomputers.api.module.author.domain.Author;

public interface CreateAuthorUseCase {
    Author create(CreateAuthorCommand command);
}
