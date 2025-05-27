package com.servinetcomputers.api.module.author.infrastructure.out.persistence;

import com.servinetcomputers.api.module.author.domain.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaAuthorMapper {
    Author toDomain(AuthorEntity entity);

    AuthorEntity toEntity(Author domain);
}
