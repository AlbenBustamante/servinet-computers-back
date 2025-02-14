package com.servinetcomputers.api.module.cashtransfer.persistence.repository;

import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import com.servinetcomputers.api.module.cashtransfer.persistence.JpaCashTransferRepository;
import com.servinetcomputers.api.module.cashtransfer.persistence.mapper.CashTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CashTransferRepositoryImpl implements CashTransferRepository {
    private final JpaCashTransferRepository repository;
    private final CashTransferMapper mapper;

    @Override
    public CashTransferDto save(CreateCashTransferDto createCashTransferDto) {
        final var entity = mapper.toEntity(createCashTransferDto);
        final var newCashTransfer = repository.save(entity);

        return mapper.toDto(newCashTransfer);
    }

    @Override
    public List<CashTransferDto> getAllBySenderIdOrReceiverId(int senderId, int receiverId) {
        final var transfers = repository.findAllBySenderIdOrReceiverIdAndEnabledTrue(senderId, receiverId);
        return mapper.toDto(transfers);
    }

    @Override
    public int sumAllBySenderId(int senderId) {
        final var sum = repository.sumAllBySenderId(senderId);
        return sum == null ? 0 : sum;
    }

    @Override
    public int sumAllByReceiverId(int receiverId) {
        final var sum = repository.sumAllByReceiverId(receiverId);
        return sum != null ? sum : 0;
    }
}
