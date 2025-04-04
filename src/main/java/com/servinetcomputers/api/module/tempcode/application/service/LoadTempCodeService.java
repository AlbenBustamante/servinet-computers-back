package com.servinetcomputers.api.module.tempcode.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.module.tempcode.application.usecase.LoadTempCodeUseCase;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeRequest;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeResponse;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
public class LoadTempCodeService implements LoadTempCodeUseCase {
    private static final int MIN = 1000;
    private static final int MAX = 10000;
    private final TempCodeRepository repository;

    @Secured(value = ADMIN_AUTHORITY)
    @Transactional(rollbackFor = AppException.class)
    @Override
    public TempCodeResponse call() {
        final var lastCode = repository.getLast();

        if (lastCode.isEmpty() || lastCode.get().getUsedBy() != null) {
            final var code = generateCode();
            final var request = new TempCodeRequest(code);
            return repository.save(request);
        }

        return lastCode.get();
    }

    private int generateCode() {
        final var random = new Random();
        final var generatedCodeOptional = random.ints(MIN, MAX).findFirst();

        return generatedCodeOptional.isPresent() ? generatedCodeOptional.getAsInt() : MAX;
    }
}
