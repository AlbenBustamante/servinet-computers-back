package com.servinetcomputers.api.module.author.application.port.out;

import com.servinetcomputers.api.module.author.domain.Author;

public interface AuthorOutputPort {
    Author create(CreateAuthorCommand command);

    boolean existsByName(String name);
}
