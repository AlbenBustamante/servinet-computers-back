package com.servinetcomputers.api.module.author.infrastructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAuthorRepository extends JpaRepository<AuthorEntity, Integer> {
}
