package com.servinetcomputers.api.module.changelog.persistence.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogDto;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.changelog.domain.repository.ChangeLogRepository;
import com.servinetcomputers.api.module.changelog.persistence.JpaChangeLogRepository;
import com.servinetcomputers.api.module.changelog.persistence.mapper.ChangeLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChangeLogRepositoryImpl implements ChangeLogRepository {
    private final JpaChangeLogRepository jpaChangeLogRepository;
    private final ChangeLogMapper changeLogMapper;

    @Override
    public ChangeLogDto save(CreateChangeLogDto dto) throws JsonProcessingException {
        final var entity = changeLogMapper.toEntity(dto);
        final var newChangeLog = jpaChangeLogRepository.save(entity);

        return changeLogMapper.toDto(newChangeLog);
    }

    @Override
    public List<ChangeLogDto> getAllByCashRegisterDetailId(int cashRegisterDetailId) {
        final var entities = jpaChangeLogRepository.findAllByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId);
        return changeLogMapper.toDto(entities);
    }
}
