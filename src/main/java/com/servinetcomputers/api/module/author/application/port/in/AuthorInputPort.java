package com.servinetcomputers.api.module.author.application.port.in;

import com.servinetcomputers.api.module.author.infrastructure.in.rest.AuthorDto;

public interface AuthorInputPort {
    AuthorDto save();
}
