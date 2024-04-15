package com.servinetcomputers.api.domain.cashregister.service;

import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterBaseMapper;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterBaseRepository;
import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.abs.ICashRegisterBaseService;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CashRegisterBaseServiceImpl implements ICashRegisterBaseService {
    private final CashRegisterBaseMapper mapper;
    private final CashRegisterBaseRepository repository;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public CashRegisterBaseResponse create(CashRegisterBaseRequest request) {
        final var detail = cashRegisterDetailRepository.findById(request.cashRegisterDetailId())
                .orElseThrow(() -> new NotFoundException("La caja registradora no fue encontrada"));

        if (detail.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("La caja registradora no fue encontrada");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public List<CashRegisterBaseResponse> getByCashRegisterDetailId(int cashRegisterDetailId) {
        final var detail = cashRegisterDetailRepository.findById(cashRegisterDetailId)
                .orElseThrow(() -> new NotFoundException("La caja registradora no fue encontrada"));

        if (detail.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("La caja registradora no fue encontrada");
        }

        return mapper.toResponses(repository.findByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId));
    }
}
