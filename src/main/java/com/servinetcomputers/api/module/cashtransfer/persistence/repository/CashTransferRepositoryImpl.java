package com.servinetcomputers.api.module.cashtransfer.persistence.repository;

import com.servinetcomputers.api.core.util.enums.CashBoxType;
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
    public List<CashTransferDto> getAllByCashBoxIdAndType(int id, CashBoxType type) {
        final var transfers = repository.findAllByCashBoxIdAndTypeAndEnabledTrue(id, type);
        return mapper.toDto(transfers);
    }

    @Override
    public int sumAllBySenderIdAndType(int senderId, CashBoxType senderType) {
        final var sum = repository.sumAllBySenderIdAndType(senderId, senderType);
        return sum == null ? 0 : sum;
    }

    @Override
    public int sumAllByReceiverIdAndType(int receiverId, CashBoxType receiverType) {
        final var sum = repository.sumAllByReceiverIdAndType(receiverId, receiverType);
        return sum != null ? sum : 0;
    }
}
