package com.servinetcomputers.api.domain.safes.service;

import com.servinetcomputers.api.domain.safes.abs.ISafeService;
import com.servinetcomputers.api.domain.safes.abs.SafeMapper;
import com.servinetcomputers.api.domain.safes.abs.SafeRepository;
import com.servinetcomputers.api.domain.safes.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeResponse;
import com.servinetcomputers.api.exception.AlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SafeServiceImpl implements ISafeService {

    private final SafeRepository repository;
    private final SafeMapper mapper;

    @Override
    public SafeResponse create(SafeRequest request) {
        if (repository.existsByNumeralAndEnabledTrue(request.numeral())) {
            throw new AlreadyExistsException("Ya exite una caja con el numeral ingresado");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public List<SafeResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrue());
    }

}
