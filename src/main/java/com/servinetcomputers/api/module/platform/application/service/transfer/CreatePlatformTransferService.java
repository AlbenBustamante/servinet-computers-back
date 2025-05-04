package com.servinetcomputers.api.module.platform.application.service.transfer;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.storage.StorageService;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.CreatePlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformTransferWithVouchersDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class CreatePlatformTransferService implements CreatePlatformTransferUseCase {
    private final PlatformTransferRepository repository;
    private final PlatformRepository platformRepository;
    private final StorageService storageService;

    /**
     * Create and persist a new transfer.
     *
     * @param request  the data to be saved.
     * @param vouchers the voucher files to upload.
     * @return the transfer saved.
     */
    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformTransferDto call(CreatePlatformTransferWithVouchersDto request, MultipartFile[] vouchers) {
        final var platform = platformRepository.get(request.getPlatformId())
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: #" + request.getPlatformId()));

        request.setPlatform(platform);

        if (vouchers != null && vouchers.length > 0) {
            final var folder = "platform_transfers/" + platform.getName().toLowerCase();

            final var voucherUrls = storageService.uploadFiles(folder, vouchers);
            request.setVoucherUrls(voucherUrls);
        }

        return repository.save(request);
    }
}
