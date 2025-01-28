package com.servinetcomputers.api.module.safes.application.service;

import com.servinetcomputers.api.core.exception.AlreadyExistsException;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.module.safes.application.usecase.CreateSafeUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeResponse;
import com.servinetcomputers.api.module.safes.domain.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateSafeService implements CreateSafeUseCase {
    private final SafeRepository repository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public SafeResponse call(SafeRequest param) {
        if (repository.existsByNumeral(param.numeral())) {
            throw new AlreadyExistsException("Ya existe una caja con el numeral: #" + param.numeral());
        }

        return repository.save(param);
    }
}
