package com.servinetcomputers.api.module.changelog.persistence.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogResponse;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.changelog.domain.repository.ChangeLogRepository;
import com.servinetcomputers.api.module.changelog.persistence.JpaChangeLogRepository;
import com.servinetcomputers.api.module.changelog.persistence.mapper.ChangeLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ChangeLogRepositoryImpl implements ChangeLogRepository {
    private final JpaChangeLogRepository jpaChangeLogRepository;
    private final ChangeLogMapper changeLogMapper;

    @Override
    public ChangeLogResponse save(CreateChangeLogDto dto) throws JsonProcessingException {
        final var entity = changeLogMapper.toEntity(dto);
        final var newChangeLog = jpaChangeLogRepository.save(entity);

        return changeLogMapper.toResponse(newChangeLog);
    }
}
