package com.servinetcomputers.api.domain.safes.service;

import com.servinetcomputers.api.domain.safes.abs.ISafeService;
import com.servinetcomputers.api.domain.safes.abs.SafeMapper;
import com.servinetcomputers.api.domain.safes.abs.SafeRepository;
import com.servinetcomputers.api.domain.safes.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeResponse;
import com.servinetcomputers.api.exception.AlreadyExistsException;
import com.servinetcomputers.api.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SafeServiceImpl implements ISafeService {

    private final SafeRepository repository;
    private final SafeMapper mapper;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public SafeResponse create(SafeRequest request) {
        if (repository.existsByNumeralAndEnabledTrue(request.numeral())) {
            throw new AlreadyExistsException("Ya exite una caja con el numeral ingresado");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SafeResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrue());
    }

}
