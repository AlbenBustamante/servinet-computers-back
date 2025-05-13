package com.servinetcomputers.api.module.tempcode.persistence.repository;

import com.servinetcomputers.api.module.tempcode.domain.dto.CreateTempCodeDto;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeDto;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import com.servinetcomputers.api.module.tempcode.persistence.JpaTempCodeRepository;
import com.servinetcomputers.api.module.tempcode.persistence.mapper.TempCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TempCodeRepositoryImpl implements TempCodeRepository {
    private final JpaTempCodeRepository jpaTempCodeRepository;
    private final TempCodeMapper tempCodeMapper;

    @Override
    public TempCodeDto save(CreateTempCodeDto request) {
        final var entity = tempCodeMapper.toEntity(request);
        final var newTempCode = jpaTempCodeRepository.save(entity);

        return tempCodeMapper.toDto(newTempCode);
    }

    @Override
    public void save(TempCodeDto response) {
        final var entity = tempCodeMapper.toEntity(response);
        jpaTempCodeRepository.save(entity);
    }

    @Override
    public Optional<TempCodeDto> getLast() {
        final var tempCode = jpaTempCodeRepository.findFirstByOrderByCreatedDateDesc();
        return tempCode.map(tempCodeMapper::toDto);
    }
}
