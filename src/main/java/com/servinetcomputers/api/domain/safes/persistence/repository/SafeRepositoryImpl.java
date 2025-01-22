package com.servinetcomputers.api.domain.safes.persistence.repository;

import com.servinetcomputers.api.core.exception.AlreadyExistsException;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeResponse;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeRepository;
import com.servinetcomputers.api.domain.safes.persistence.JpaSafeRepository;
import com.servinetcomputers.api.domain.safes.persistence.mapper.SafeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SafeRepositoryImpl implements SafeRepository {
    private final JpaSafeRepository repository;
    private final SafeMapper mapper;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public SafeResponse create(SafeRequest request) {
        if (repository.existsByNumeralAndEnabledTrue(request.numeral())) {
            throw new AlreadyExistsException("Ya existe una caja con el numeral ingresado");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SafeResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrue());
    }
}
