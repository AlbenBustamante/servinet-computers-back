package com.servinetcomputers.api.domain.safes.application.service;

import com.servinetcomputers.api.domain.safes.application.usecase.GetAllSafesUseCase;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeResponse;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeRepository;
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
    public List<SafeResponse> call() {
        return repository.getAll();
    }
}
