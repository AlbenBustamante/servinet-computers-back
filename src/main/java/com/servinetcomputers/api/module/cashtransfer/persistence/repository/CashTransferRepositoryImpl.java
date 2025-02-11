package com.servinetcomputers.api.module.cashtransfer.persistence.repository;

import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CashTransferRepositoryImpl implements CashTransferRepository {
    @Override
    public CashTransferDto save(CreateCashTransferDto createCashTransferDto) {
        return null;
    }

    @Override
    public List<CashTransferDto> getAllByUserId(int userId) {
        return List.of();
    }
}
