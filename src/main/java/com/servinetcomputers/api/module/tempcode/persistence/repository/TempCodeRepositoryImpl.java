package com.servinetcomputers.api.module.tempcode.persistence.repository;

import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeRequest;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeResponse;
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
    public TempCodeResponse save(TempCodeRequest request) {
        final var entity = tempCodeMapper.toEntity(request);
        final var newTempCode = jpaTempCodeRepository.save(entity);

        return tempCodeMapper.toResponse(newTempCode);
    }

    @Override
    public Optional<TempCodeResponse> getLast() {
        final var tempCode = jpaTempCodeRepository.findFirstByOrderByCreatedDateDesc();
        return tempCode.map(tempCodeMapper::toResponse);
    }
}
