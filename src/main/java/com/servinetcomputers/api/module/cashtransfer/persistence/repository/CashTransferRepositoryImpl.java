package com.servinetcomputers.api.module.cashtransfer.persistence.repository;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.page.PaginationMapper;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.repository.CashTransferRepository;
import com.servinetcomputers.api.module.cashtransfer.persistence.JpaCashTransferRepository;
import com.servinetcomputers.api.module.cashtransfer.persistence.mapper.CashTransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CashTransferRepositoryImpl implements CashTransferRepository {
    private final JpaCashTransferRepository repository;
    private final CashTransferMapper mapper;
    private final PaginationMapper paginationMapper;

    @Override
    public CashTransferDto save(CreateCashTransferDto createCashTransferDto) {
        final var entity = mapper.toEntity(createCashTransferDto);
        final var newCashTransfer = repository.save(entity);

        return mapper.toDto(newCashTransfer);
    }

    @Override
    public CashTransferDto save(CashTransferDto dto) {
        final var entity = mapper.toEntity(dto);
        final var newCashTransfer = repository.save(entity);

        return mapper.toDto(newCashTransfer);
    }

    @Override
    public Optional<CashTransferDto> get(int cashTransferId) {
        final var cashTransfer = repository.findByIdAndEnabledTrue(cashTransferId);
        return cashTransfer.map(mapper::toDto);
    }

    @Override
    public PageResponse<CashTransferDto> getAllByCashBoxIdAndType(int id, CashBoxType type, Pageable pageable) {
        final var page = repository.findAllByCashBoxIdAndTypeAndEnabledTrue(id, type, pageable);
        final var transfers = mapper.toDto(page.getContent());

        return new PageResponse<>(paginationMapper.toPagination(page), transfers);
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
