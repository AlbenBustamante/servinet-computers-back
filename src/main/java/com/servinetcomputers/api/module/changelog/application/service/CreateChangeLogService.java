package com.servinetcomputers.api.module.changelog.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.changelog.application.usecase.CreateChangeLogUseCase;
import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogResponse;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.changelog.domain.repository.ChangeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateChangeLogService implements CreateChangeLogUseCase {
    private final ChangeLogRepository repository;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;

    @Transactional(rollbackFor = {JsonMappingException.class, AppException.class})
    @Override
    public ChangeLogResponse call(CreateChangeLogDto dto) {
        try {
            final var cashRegisterDetail = cashRegisterDetailRepository.get(dto.getCashRegisterDetailId())
                    .orElseThrow(() -> new NotFoundException("No se encontró la jornada: " + dto.getCashRegisterDetailId()));

            dto.setCashRegisterDetail(cashRegisterDetail);

            return repository.save(dto);
        } catch (JsonProcessingException ex) {
            throw new AppException("Error processing JSON: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
