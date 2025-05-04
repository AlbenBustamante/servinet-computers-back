package com.servinetcomputers.api.module.safes.application.service;

import com.servinetcomputers.api.core.util.constants.SecurityConstants;
import com.servinetcomputers.api.module.safes.application.usecase.GetAllSafeDetailsByIdUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllSafeDetailsByIdService implements GetAllSafeDetailsByIdUseCase {
    private final SafeDetailRepository safeDetailRepository;

    @Secured(value = SecurityConstants.ADMIN_AUTHORITY)
    @Transactional(readOnly = true)
    @Override
    public List<SafeDetailDto> call(Integer safeId) {
        return safeDetailRepository.getAllBySafeId(safeId);
    }
}
