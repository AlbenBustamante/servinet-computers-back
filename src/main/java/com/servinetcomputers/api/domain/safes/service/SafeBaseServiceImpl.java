package com.servinetcomputers.api.domain.safes.service;

import com.servinetcomputers.api.domain.safes.abs.ISafeBaseService;
import com.servinetcomputers.api.domain.safes.abs.SafeBaseMapper;
import com.servinetcomputers.api.domain.safes.abs.SafeBaseRepository;
import com.servinetcomputers.api.domain.safes.abs.SafeRepository;
import com.servinetcomputers.api.domain.safes.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeBaseResponse;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SafeBaseServiceImpl implements ISafeBaseService {

    private final SafeBaseRepository repository;
    private final SafeBaseMapper mapper;
    private final SafeRepository safeRepository;

    @Override
    public SafeBaseResponse create(SafeBaseRequest request) {
        final var safe = safeRepository.findByIdAndEnabledTrue(request.safeId())
                .orElseThrow(() -> new NotFoundException("No se encontró la caja con id #" + request.safeId()));

        final var safeBase = mapper.toEntity(request);
        safeBase.setSafe(safe);

        return mapper.toResponse(repository.save(safeBase));
    }

}
