package com.servinetcomputers.api.module.safes.application.service;

import com.servinetcomputers.api.module.safes.application.usecase.GetAllSafesUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllSafesService implements GetAllSafesUseCase {
    private final SafeRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<SafeDto> call() {
        return repository.getAll();
    }
}
