package com.servinetcomputers.api.module.author.infrastructure.in.rest;

import com.servinetcomputers.api.module.author.domain.Author;

public interface AuthorMapper {
    Author toDomain(AuthorDto dto);

    AuthorDto toDto(Author domain);
}
