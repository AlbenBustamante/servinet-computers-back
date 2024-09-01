package com.servinetcomputers.api.domain.platform.service;

import com.servinetcomputers.api.domain.platform.abs.IPlatformTransferService;
import com.servinetcomputers.api.domain.platform.abs.PlatformRepository;
import com.servinetcomputers.api.domain.platform.abs.PlatformTransferMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformTransferRepository;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.NotFoundException;
import com.servinetcomputers.api.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * The platform transfer's service implementation.
 */
@RequiredArgsConstructor
@Service
public class PlatformTransferServiceImpl implements IPlatformTransferService {

    private final PlatformTransferRepository repository;
    private final PlatformTransferMapper mapper;
    private final PlatformRepository platformRepository;
    private final StorageService storageService;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformTransferResponse create(PlatformTransferRequest request, MultipartFile[] vouchers) {
        final var platform = platformRepository.findById(request.getPlatformId())
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: #" + request.getPlatformId()));

        if (!platform.getEnabled()) {
            throw new NotFoundException("Plataforma no encontrada: #" + request.getPlatformId());
        }

        final var transfer = mapper.toEntity(request);

        if (vouchers != null && vouchers.length > 0) {
            final var folder = "platform_transfers/" + platform.getName().toLowerCase();

            final var voucherUrls = storageService.uploadFiles(folder, vouchers);
            transfer.setVoucherUrls(voucherUrls);
        }

        return mapper.toResponse(repository.save(transfer));
    }

    @Transactional(readOnly = true)
    @Override
    public PlatformTransferResponse get(int transferId) {
        final var transfer = repository.findById(transferId)
                .orElseThrow(() -> new NotFoundException("Transferencia no encontrada: " + transferId));

        if (transfer.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Transferencia no encontrada: " + transferId);
        }

        return mapper.toResponse(transfer);
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformTransferResponse update(int transferId, PlatformTransferRequest request) {
        final var transfer = repository.findById(transferId)
                .orElseThrow(() -> new NotFoundException("Transferencia no encontrada: " + transferId));

        if (transfer.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Transferencia no encontrada: " + transferId);
        }

        if (request.getPlatformId() != null) {
            final var platform = platformRepository.findById(request.getPlatformId())
                    .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: #" + request.getPlatformId()));

            if (!platform.getEnabled()) {
                throw new NotFoundException("Plataforma no encontrada: #" + request.getPlatformId());
            }

            transfer.setPlatform(platform);
        }

        transfer.setValue(request.getValue() != null ? request.getValue() : transfer.getValue());

        return mapper.toResponse(repository.save(transfer));
    }

    @Override
    public boolean delete(int transferId) {
        final var transferFound = repository.findById(transferId);

        if (transferFound.isEmpty()) {
            return false;
        }

        transferFound.get().setEnabled(false);

        repository.save(transferFound.get());

        return true;
    }

}
